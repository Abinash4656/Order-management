package com.ordermanagement.orders.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.orders.DTOs.OrderResponseTO;
import com.ordermanagement.orders.requestatributes.OrderRequest;
import com.ordermanagement.orders.service.OrderService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Order API", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

	@Autowired
	OrderService orderService;

	@GetMapping(value = "/orders")
	public ResponseEntity<Object> getAllOrders() {
		try {
			List<OrderResponseTO> orders = orderService.getOrders();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("no order found", HttpStatus.OK);
		}
		
		
	}

	@PostMapping(value = "/orders")
	public ResponseEntity<String> saveOrders(@Valid
			@RequestBody(required = true) OrderRequest orderRequest) {
		try {
		    orderService.saveOrder(orderRequest);
			return new ResponseEntity<>( "saved successfully", HttpStatus.OK);

		}catch (RuntimeException e) {
			if(e instanceof TransactionSystemException) {
			return new ResponseEntity<>("invalid request", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("couldn't save", HttpStatus.INTERNAL_SERVER_ERROR);	
			}
		}
	}
}
