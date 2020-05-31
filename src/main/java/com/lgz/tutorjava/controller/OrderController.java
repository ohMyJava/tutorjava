package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.service.OrderService;
import com.lgz.tutorjava.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理--订单管理控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public Message getOrders(@Param("limit") Integer limit,
                             @Param("page") Integer page,
                             @Param("condition") String condition){
        Message msg = new Message();
        try {
            msg.setData(orderService.getOrders(page,limit,condition));
            msg.setInfo("6666","查询订单成功");
            LOGGER.info("查询订单成功！limit="+limit+";page="+page+";condition="+condition);
        }catch (Exception e){
            msg.setInfo("7777","查询订单过程出现异常：");
            LOGGER.info("查询订单过程出现异常："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getOrdersNum")
    public Message getOrdersNum(String condition){
        Message msg = new Message();
        try {
            msg.setData(orderService.getOrdersNum(condition));
            msg.setInfo("6666","查询订单数量成功！");
            LOGGER.info("查询订单数量成功！");
        }catch (Exception e){
            msg.setInfo("7777","查询订单数量失败！");
            LOGGER.warn("查询订单数量出现异常："+e.getMessage());
        }
        return msg;
    }
}
