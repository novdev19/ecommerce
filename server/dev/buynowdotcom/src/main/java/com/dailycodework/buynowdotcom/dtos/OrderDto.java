package com.dailycodework.buynowdotcom.dtos;

import com.dailycodework.buynowdotcom.enums.OrderStatus;
import com.dailycodework.buynowdotcom.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDto> items;

}
