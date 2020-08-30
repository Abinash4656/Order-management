package com.ordermanagement.orders.extservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ordermanagement.orders.requestatributes.ItemRequest;

@FeignClient(url = "http://localhost:8080",name = "ITEMS")
public interface OrderItemClient {

@GetMapping(value = "/items")
public List<ItemResponse> getAllItems();
@PostMapping(value = "/items")
public String saveItems(List<ItemRequest> ItemRequests);
@GetMapping(value = "/item/{productId}")
public ItemResponse getItemById(@PathVariable(value = "productId") Integer productId);
}
