package com.ordermanagement.orderitem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/*
 * h2 low level entity which is mapped to data base
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderItem {
@Id	
private Integer productCode;
@NotNull
@NotBlank
private String productName;
private Integer availablQuantity;
private Long unitPrice;
}
