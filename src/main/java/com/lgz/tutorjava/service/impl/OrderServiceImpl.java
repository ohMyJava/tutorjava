package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.OrderMapper;
import com.lgz.tutorjava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Map<String,Object>> getOrders(Integer page,Integer limit,String condition){
        return orderMapper.getOrders((page-1)*limit,limit,condition);
    }

    @Override
    public Integer getOrdersNum(String condition){
        return orderMapper.getOrdersNum(condition);
    }
}
