package com.restaurant.dianca.controller;

import com.restaurant.dianca.dto.OrderSubmitDTO;
import com.restaurant.dianca.entity.ResOrder;
import com.restaurant.dianca.entity.ResOrderItem;
import com.restaurant.dianca.mapper.ResOrderItemMapper;
import com.restaurant.dianca.mapper.ResOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin // 允许跨域
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private ResOrderMapper orderMapper;

    @Autowired
    private ResOrderItemMapper orderItemMapper;

    @PostMapping("/submit")
    public String submitOrder(@RequestBody OrderSubmitDTO orderDto) {
        // 1. 创建订单主表记录
        ResOrder order = new ResOrder();
        // 用时间戳生成一个简单的订单号 (例如: ORD1688888888)
        order.setOrderNo("ORD" + System.currentTimeMillis());
        order.setTableNumber(orderDto.getTableNumber());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setFinalAmount(orderDto.getTotalAmount());
        order.setStatus(0); // 0表示刚下单，待厨房确认
        order.setCreateTime(LocalDateTime.now());

        // 插入主表 (插入成功后，MyBatis-Plus 会自动把生成的自增 ID 塞回 order 对象里)
        orderMapper.insert(order);

        // 2. 循环遍历前端发来的购物车菜品，写入明细表
        for (OrderSubmitDTO.OrderItemDTO itemDto : orderDto.getItems()) {
            ResOrderItem item = new ResOrderItem();
            item.setOrderId(order.getId()); // 关联刚才生成的主单 ID
            item.setDishId(itemDto.getId());
            item.setDishName(itemDto.getName());
            item.setUnitPrice(itemDto.getPrice());
            item.setQuantity(itemDto.getQuantity());

            orderItemMapper.insert(item);
        }

        return "订单提交成功！订单号：" + order.getOrderNo();
    }
}