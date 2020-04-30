package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Tutor;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/23 15:56
 */
public interface TutorService {

    List<Tutor> getTutors(Integer limit,
                          Integer page,
                          String condition);

    int tutorNumber(String condition);

    int delTutor(String delList);

    Tutor getOneTutor(Integer tutorId);

    int addTutor(Tutor tutor);

    int modifyTutor(Tutor tutor);
}
