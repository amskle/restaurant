package com.restaurant.dianca.entity;



import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("res_table")
public class ResTable {
    private Long id;
    private Integer tableNumber;
    private Integer status; // 0:空闲, 1:占用, 2:预订, 3:清理中
    private Integer capacity;
    private String customerName;
    private LocalDateTime occupiedTime;
    private Long currentOrderId;
}