package com.ordermanagement.orders.requestatributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/*
 * Request pojo to intercept input values
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemRequest {
	private Integer productCode;
	private String productName;
	private Integer quantity;
	private Integer price;
	
}
