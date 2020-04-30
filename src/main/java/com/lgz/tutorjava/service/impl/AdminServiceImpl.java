package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.AdminMapper;
import com.lgz.tutorjava.model.Admin;
import com.lgz.tutorjava.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/29 15:07
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdmins(Integer power){
        return adminMapper.getAdmins(power);
    }
}
