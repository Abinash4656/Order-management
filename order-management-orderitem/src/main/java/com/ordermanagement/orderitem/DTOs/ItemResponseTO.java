package com.ordermanagement.orderitem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemResponseTO {
	private Integer productCode;
	private String productName;
	private Integer availableQuantity;
	private Long unitPrice;
}
