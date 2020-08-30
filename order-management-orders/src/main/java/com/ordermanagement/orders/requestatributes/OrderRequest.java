package com.ordermanagement.orders.requestatributes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
	private String orderId;	
	private String customerName; 
	private String orderDate;
	private String shippingAddress;
	List<ItemRequest> items;
}
