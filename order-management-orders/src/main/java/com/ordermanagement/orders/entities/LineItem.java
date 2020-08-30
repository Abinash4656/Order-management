package com.ordermanagement.orders.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class LineItem {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long lineId;

private Integer quantity;
@NotBlank
@NotNull
private String orderId;

private Integer productCode;


}
