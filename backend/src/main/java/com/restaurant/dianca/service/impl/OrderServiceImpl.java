package com.restaurant.dianca.service.impl;

import com.restaurant.dianca.dto.OrderSubmitDTO;
import com.restaurant.dianca.entity.ResOrder;
import com.restaurant.dianca.entity.ResOrderItem;
import com.restaurant.dianca.mapper.ResOrderItemMapper;
import com.restaurant.dianca.mapper.ResOrderMapper;
import com.restaurant.dianca.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ResOrderMapper orderMapper;

    @Autowired
    private ResOrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public String submitOrder(OrderSubmitDTO orderDto) {
        ResOrder order = new ResOrder();
        order.setOrderNo("ORD" + System.currentTimeMillis());
        order.setTableNumber(orderDto.getTableNumber());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setFinalAmount(orderDto.getTotalAmount());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());

        orderMapper.insert(order);

        for (OrderSubmitDTO.OrderItemDTO itemDto : orderDto.getItems()) {
            ResOrderItem item = new ResOrderItem();
            item.setOrderId(order.getId());
            item.setDishId(itemDto.getId());
            item.setDishName(itemDto.getName());
            item.setUnitPrice(itemDto.getPrice());
            item.setQuantity(itemDto.getQuantity());

            orderItemMapper.insert(item);
        }

        return "订单提交成功！订单号：" + order.getOrderNo();
    }
}
