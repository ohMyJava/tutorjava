package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/29 14:08
 * 订单SQL接口
 */
@Repository
public interface OrderMapper {

    Integer addOrder(Order order);

    List<Map<String,Object>> getOrders(@Param("page") Integer page,
                                       @Param("limit") Integer limit,
                                       @Param("condition") String condition);

    Integer getOrdersNum(@Param("condition") String condition);
}
