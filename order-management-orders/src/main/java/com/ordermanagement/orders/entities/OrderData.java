package com.ordermanagement.orders.entities;

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
public class OrderData {
@Id	
@NotBlank
@NotNull
private String orderId;	
@NotBlank
@NotNull
private String customerName; 
@NotBlank
@NotNull
private String orderDate;
@NotBlank
@NotNull
private String shippingAddress;
private long total;
}
