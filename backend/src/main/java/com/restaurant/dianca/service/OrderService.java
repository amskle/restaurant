package com.restaurant.dianca.service;

import com.restaurant.dianca.dto.OrderSubmitDTO;

public interface OrderService {
    String submitOrder(OrderSubmitDTO orderDto);
}
