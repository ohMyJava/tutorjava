package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.TutorMapper;
import com.lgz.tutorjava.model.Tutor;
import com.lgz.tutorjava.service.TutorService;
import com.lgz.tutorjava.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/23 15:56
 */
@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorMapper tutorMapper;

    @Override
    public List<Tutor> getTutors(Integer limit,
                                 Integer page,
                                 String condition){
        return tutorMapper.getTutors(limit,(page-1)*limit,condition);
    }

    @Override
    public int tutorNumber(String condition){
        return tutorMapper.tutorNumber(condition);
    }

    @Override
    public int delTutor(String delList){
        return tutorMapper.delTutor(DateUtil.currDate(),delList);
    }

    @Override
    public Tutor getOneTutor(Integer tutorId){
        return tutorMapper.getOneTutor(tutorId);
    }

    @Override
    public int addTutor(Tutor tutor){
        tutor.setRegTime(DateUtil.currDate());
        return tutorMapper.addTutor(tutor);
    }

    @Override
    public int modifyTutor(Tutor tutor){
        tutor.setUpdateTime(DateUtil.currDate());
        return tutorMapper.modifyTutor(tutor);
    }
}
