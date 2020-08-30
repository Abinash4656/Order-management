package com.ordermanagement.orders.DTOs;

import java.util.List;

import com.ordermanagement.orders.extservice.ItemResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponseTO {
	private String orderId;	
	private String customerName; 
	private String orderDate;
	private String shippingAddress;
	private Long totalPrice;
	private List<ItemResponseTO> items;
}
