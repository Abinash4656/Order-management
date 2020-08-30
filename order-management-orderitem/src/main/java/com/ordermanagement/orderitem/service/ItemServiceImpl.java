package com.ordermanagement.orderitem.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ordermanagement.orderitem.DTOs.ItemResponseTO;
import com.ordermanagement.orderitem.entities.OrderItem;
import com.ordermanagement.orderitem.exception.ItemNotFoundException;
import com.ordermanagement.orderitem.exception.SaveItemException;
import com.ordermanagement.orderitem.itemrepository.OrderItemRepository;
import com.ordermanagement.orderitem.requestatributes.ItemRequest;
/*
 * All service methods implemented using spring JPA
 * */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	OrderItemRepository OrderItemDAO;
	@Override
	public void saveItem(ItemRequest saveRequest) throws SaveItemException {
		try {
			OrderItem item = new OrderItem(saveRequest.getProductCode(), saveRequest.getProductName(),
					saveRequest.getQuantity(),saveRequest.getPrice());
			OrderItemDAO.save(item);
		} catch (Exception e) {
			throw new SaveItemException("Error while saving item");
		}
	}

	/*
	 * Save multiple items in a single go
	 */
	@Override
	@Transactional
	public void saveItems(List<ItemRequest> saveRequests) throws SaveItemException {
		try {
			for (ItemRequest saveRequest : saveRequests) {
				OrderItem item = new OrderItem(saveRequest.getProductCode(), saveRequest.getProductName(),
						saveRequest.getQuantity(),saveRequest.getPrice());
				OrderItemDAO.save(item);
			}
		} catch (Exception e) {
			throw new SaveItemException("Error while saving item");
		}
	}

	@Override
	public List<ItemResponseTO> getItems() throws ItemNotFoundException {
		try {
			List<ItemResponseTO> responseList = new ArrayList<>();
			Iterable<OrderItem> allItems = OrderItemDAO.findAll();
			Iterator<OrderItem> items = allItems.iterator();
			while (items.hasNext()) {
				OrderItem item = items.next();
				responseList.add(new ItemResponseTO(item.getProductCode(), item.getProductName(),
						item.getAvailablQuantity(), item.getUnitPrice()));
			}
			if (!CollectionUtils.isEmpty(responseList)) {
				return responseList;
			} else {
				throw new ItemNotFoundException("No items found");
			}
		} catch (Exception e) {
			throw new ItemNotFoundException(e.getMessage());
		}
	}
	@Override
	public ItemResponseTO getItemById(Integer productCode) {
		ItemResponseTO itemResponseTO = null;
		try {
		Optional<OrderItem> findById = OrderItemDAO.findById(productCode);
		if(findById.isPresent()) {
			OrderItem orderItem = findById.get();
			itemResponseTO = new ItemResponseTO(orderItem.getProductCode(), orderItem.getProductName(), 
					orderItem.getAvailablQuantity(), orderItem.getUnitPrice());
		} else {
			throw new ItemNotFoundException("invalid product code");
		}
		}catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
		return itemResponseTO;
	}
}
