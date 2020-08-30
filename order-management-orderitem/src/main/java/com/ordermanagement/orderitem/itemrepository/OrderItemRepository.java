package com.ordermanagement.orderitem.itemrepository;

import org.springframework.data.repository.CrudRepository;

import com.ordermanagement.orderitem.entities.OrderItem;
/*
 * JPA Crud Repository to perform all read, write etc
 * */

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
