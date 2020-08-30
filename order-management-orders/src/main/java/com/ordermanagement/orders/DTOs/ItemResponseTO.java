package com.ordermanagement.orders.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemResponseTO {
	private String productName;
	private Integer quantity;
	private Integer price;

}
