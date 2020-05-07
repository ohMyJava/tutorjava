package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Tutor;

import java.util.List;

/**
 * @author lgz
 * @date 2020/5/7 9:00
 */
public interface PageTutorService {
    List<Tutor> getTutors(String able,String school,String location,Integer page,Integer limit);

    Tutor getOneTutor(Integer tutorId);

    Integer getNumbers(String able,String school,String location);
}
