package com.ordermanagement.orderitem.service;

import java.util.List;

import com.ordermanagement.orderitem.DTOs.ItemResponseTO;
import com.ordermanagement.orderitem.exception.ItemNotFoundException;
import com.ordermanagement.orderitem.exception.SaveItemException;
import com.ordermanagement.orderitem.requestatributes.ItemRequest;
/*
 * Business service implementation for order items
 * example: read and write operations
 * */
public interface ItemService {
	public void saveItem(ItemRequest saveRequest) throws SaveItemException;
	public void saveItems(List<ItemRequest> saveRequests) throws SaveItemException;
	public List<ItemResponseTO> getItems() throws ItemNotFoundException;
	public ItemResponseTO getItemById(Integer productCode);
}
