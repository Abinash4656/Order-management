package com.ordermanagement.orders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ordermanagement.orders.entities.LineItem;

public interface LineItemrepository extends CrudRepository<LineItem, Long>{

}
