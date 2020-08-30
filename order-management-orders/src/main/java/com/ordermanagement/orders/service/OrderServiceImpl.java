package com.ordermanagement.orders.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ordermanagement.orders.DTOs.ItemResponseTO;
import com.ordermanagement.orders.DTOs.OrderResponseTO;
import com.ordermanagement.orders.entities.LineItem;
import com.ordermanagement.orders.entities.OrderData;
import com.ordermanagement.orders.exception.OrderNotFoundException;
import com.ordermanagement.orders.exception.SaveInteruptionException;
import com.ordermanagement.orders.extservice.OrderItemClient;
import com.ordermanagement.orders.extservice.ItemResponse;
import com.ordermanagement.orders.repositories.LineItemrepository;
import com.ordermanagement.orders.repositories.OrdersRepository;
import com.ordermanagement.orders.requestatributes.ItemRequest;
import com.ordermanagement.orders.requestatributes.OrderRequest;
@Service
public class OrderServiceImpl implements OrderService{
@Autowired
OrdersRepository ordersRepo;
@Autowired
OrderItemClient extClient;
@Autowired
LineItemrepository lineItemrepository;
@Override
public void saveOrder(OrderRequest orderRequest){

            int total = 0;
            List<ItemRequest> updateRequests = new ArrayList<>();
            List<LineItem> updateLineItems = new ArrayList<>();
			for(ItemRequest item:orderRequest.getItems()) {
				ItemResponse extResponse = extClient.getItemById(item.getProductCode());
				if(null != extResponse) {
					total = total + (item.getQuantity()*extResponse.getUnitPrice());
					ItemRequest itemUpdateRequest = new ItemRequest(extResponse.getProductCode(), 
							extResponse.getProductName(), extResponse.getAvailableQuantity()-item.getQuantity(),
							extResponse.getUnitPrice());
					updateRequests.add(itemUpdateRequest);
					LineItem lineItem = new LineItem();
					lineItem.setOrderId(orderRequest.getOrderId());
					lineItem.setProductCode(item.getProductCode());
					lineItem.setQuantity(item.getQuantity());
					updateLineItems.add(lineItem);
					
				}
			}
			placeOrderServices(orderRequest, total, updateRequests, updateLineItems);
            
	
}
@Transactional
public void placeOrderServices(OrderRequest orderRequest, int total, List<ItemRequest> updateRequests,
		List<LineItem> updateLineItems) {
	OrderData order = new OrderData(orderRequest.getOrderId(), orderRequest.getCustomerName(), 
			orderRequest.getOrderDate(), orderRequest.getShippingAddress(),total);
	ordersRepo.save(order);
	extClient.saveItems(updateRequests);
	lineItemrepository.saveAll(updateLineItems);
}
@Override
public List<OrderResponseTO> getOrders() throws OrderNotFoundException{
	final List<OrderResponseTO> responseList = new ArrayList<>();
	List<OrderData> orderList = new ArrayList<>();
	List<LineItem> lineItemList = new ArrayList<>();
	List<ItemResponse> allItems = extClient.getAllItems();
	if(CollectionUtils.isEmpty(allItems)) {
		throw new OrderNotFoundException("no order found");
	}
	Iterable<OrderData> allOrders = ordersRepo.findAll();
	Iterable<LineItem> allLinetems = lineItemrepository.findAll();
	Iterator<OrderData> iterator = allOrders.iterator();
	while(iterator.hasNext()) {
		orderList.add(iterator.next());
	}
	Iterator<LineItem> iterator2 = allLinetems.iterator();
	while(iterator2.hasNext()) {
		lineItemList.add(iterator2.next());
	}
   
   Map<Integer, ItemResponse> productMap = allItems.stream().
		   collect(Collectors.toMap(ItemResponse::getProductCode, response -> response));
   
   Map<String, List<LineItem>> lineItemMap = lineItemList.stream().
		   collect(Collectors.groupingBy(LineItem::getOrderId));
   
   orderList.forEach(obj ->{
	   List<ItemResponseTO> itemTOList = new ArrayList<>();
	   lineItemMap.get(obj.getOrderId()).forEach(lineItem ->{
		   ItemResponse itemResponse = productMap.get(lineItem.getProductCode());
		   ItemResponseTO item = new ItemResponseTO(itemResponse.getProductName(),
				   lineItem.getQuantity(), lineItem.getQuantity()*itemResponse.getUnitPrice());
		   itemTOList.add(item);
	   });;
	   OrderResponseTO response = new OrderResponseTO(obj.getOrderId(), 
			   obj.getCustomerName(), obj.getOrderDate(), obj.getShippingAddress(),
			   obj.getTotal(),itemTOList);
	   
	   responseList.add(response);
   });
	return responseList;
}

}
