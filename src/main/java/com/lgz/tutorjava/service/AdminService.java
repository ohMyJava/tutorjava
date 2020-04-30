package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Admin;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/29 15:07
 */
public interface AdminService {
    List<Admin> getAdmins(Integer power);
}
