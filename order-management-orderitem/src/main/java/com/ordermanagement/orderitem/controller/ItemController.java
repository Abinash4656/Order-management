package com.ordermanagement.orderitem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordermanagement.orderitem.DTOs.ItemResponseTO;
import com.ordermanagement.orderitem.requestatributes.ItemRequest;
import com.ordermanagement.orderitem.service.ItemService;

import io.swagger.annotations.Api;

/*
 * rest APIs contain resources to perform read and write operation 
 * order item
 * */

@RestController
@Api(value = "Order Item API", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

public class ItemController {

	@Autowired
	ItemService ItemService;
//    /*
//     * create order item onw at a time
//     * */
//	@PostMapping(value = "/saveItem")
//	public ResponseEntity<String> saveItem(@RequestBody(required = true) ItemRequest itemRequest) {
//		try {
//			ItemService.saveItem(itemRequest);
//			return new ResponseEntity<>("saved successfully", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>("couldn't save", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	/*
	 * fetch all items from items
	 * its a bulk fetch implementation
	 * */
	@GetMapping(value = "/items", produces = { "application/json" })
	public ResponseEntity<String> getItems() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ItemResponseTO> items = ItemService.getItems();
			String response = mapper.writeValueAsString(items);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No items found", HttpStatus.NOT_FOUND);
		}
	}
	/*
	 * bulk save implementation for items
	 * */
	@PostMapping(value = "/items")
	public ResponseEntity<String> saveItems(@Valid@RequestBody(required = true) List<ItemRequest> itemRequestList) {
		try {
			ItemService.saveItems(itemRequestList);
			return new ResponseEntity<>("saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("couldn't save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/item/{productId}")
	public ResponseEntity<ItemResponseTO> getItemById(@PathVariable(value = "productId") Integer productId) {
		ItemResponseTO itemById = ItemService.getItemById(productId);
		if(null != itemById) {
			return new ResponseEntity<>(itemById, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
