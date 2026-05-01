package com.restaurant.dianca.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderSubmitDTO {
    private Integer tableNumber;
    private BigDecimal totalAmount;
    private List<OrderItemDTO> items; // 对应前端的 cart 数组

    @Data
    public static class OrderItemDTO {
        private Long id; // 前端的 dishId
        private String name;
        private BigDecimal price;
        private Integer quantity;
    }
}