package com.dailycodework.buynowdotcom.service.order;

import com.dailycodework.buynowdotcom.dtos.OrderDto;
import com.dailycodework.buynowdotcom.dtos.ProductDto;
import com.dailycodework.buynowdotcom.model.Cart;
import com.dailycodework.buynowdotcom.model.Order;
import com.dailycodework.buynowdotcom.model.Product;

import java.util.List;

public interface IOrderService{
    Order placeOrder(Long userId);

  //  List<Order> getUserOrders(Long userId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
