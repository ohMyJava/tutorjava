package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @author lgz
 * @date 2020/5/29 14:08
 * 订单SQL接口
 */
@Repository
public interface OrderMapper {

    Integer addOrder(Order order);
}
