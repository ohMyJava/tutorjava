package com.lgz.tutorjava.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Map<String,Object>> getOrders(Integer page,Integer limit,String condition);

    Integer getOrdersNum(String condition);
}
