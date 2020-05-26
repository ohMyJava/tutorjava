package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Tutor;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 9:00
 */
public interface PageTutorService {
    List<Tutor> getTutors(String able,String school,String location,Integer page,Integer limit,boolean flag);

    Tutor getOneTutor(Integer tutorId,boolean flag);

    Integer getNumbers(String able,String school,String location);

    Integer invite(Map<String,Object> map);

    List<Map<String,Object>> getUserStudent(String userName);
}
