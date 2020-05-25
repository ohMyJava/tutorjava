package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PageTutorMapper;
import com.lgz.tutorjava.model.Tutor;
import com.lgz.tutorjava.service.PageTutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgz
 * @date 2020/5/7 9:00
 */
@Service
public class PageTutorServiceImpl implements PageTutorService {
    @Autowired
    private PageTutorMapper pageTutorMapper;

    @Override
    public List<Tutor> getTutors(String able,String school,
                                 String location,Integer page,
                                 Integer limit,boolean flag){
        page=(page-1)*limit;
        List<Tutor> list = pageTutorMapper.getTutors(able,school,location,page,limit);
        for (int i=0;i<list.size();i++){
            Tutor tutor=list.get(i);
            //姓名处理
            tutor.setTutorName(tutor.getTutorName().substring(0,1).concat("教员"));

            //如果未登录，则进行学历和所在地信息处理
            if (!flag){
                //学历处理
                String schoolInfo=tutor.getTutorSchool();
                tutor.setTutorSchool("****".concat(schoolInfo.substring(schoolInfo.length()-2,schoolInfo.length())));
                //所在地点隐藏
                tutor.setTutorLocation(tutor.getTutorLocation().substring(0,3).concat("**市**区"));
            }
        }
        return list;
    }

    @Override
    public Tutor getOneTutor(Integer tutorId,boolean flag){
        Tutor tutor=pageTutorMapper.getOneTutor(tutorId);
        //如果当前用户未登录，则隐藏教育信息、所在地；否则只隐藏姓名和手机号
        if (!flag){
            //教育信息隐藏
            String school=tutor.getTutorSchool();
            tutor.setTutorSchool("****".concat(school.substring(school.length()-2,school.length())));
            //所在地点隐藏
            String location=tutor.getTutorLocation();
            tutor.setTutorLocation(location.substring(0,3).concat("**市**区"));
        }

        //家教姓名改为：姓氏+**
        tutor.setTutorName(tutor.getTutorName().substring(0,1).concat("教员"));
        //将手机号转为133****2222的格式
        StringBuilder phoneNumber=new StringBuilder(tutor.getPhoneNumber());
        tutor.setPhoneNumber(phoneNumber.replace(3,7,"****").toString());
        return tutor;
    }

    @Override
    public Integer getNumbers(String able,String school,String location){
        return pageTutorMapper.getNumbers(able,school,location);
    }
}
