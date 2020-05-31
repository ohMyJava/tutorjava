package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PageTutorMapper;
import com.lgz.tutorjava.dao.StudentMapper;
import com.lgz.tutorjava.dao.TutorMapper;
import com.lgz.tutorjava.model.Tutor;
import com.lgz.tutorjava.service.PageTutorService;
import com.lgz.tutorjava.utils.DateUtil;
import com.lgz.tutorjava.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 9:00
 */
@Service
public class PageTutorServiceImpl implements PageTutorService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PageTutorServiceImpl.class);

    @Autowired
    private PageTutorMapper pageTutorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TutorMapper tutorMapper;

    @Override
    public List<Tutor> getTutors(String able,String school,
                                 String location,Integer page,
                                 Integer limit,boolean flag){
        page=(page-1)*limit;
        List<Tutor> list = pageTutorMapper.getTutors(able,school,location,page,limit);
        System.out.println("-----------------------------------");
        LOGGER.info(JsonUtil.objectToJson(list));
        System.out.println("-----------------------------------");
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

    @Override
    public Integer invite(Map<String,Object> map){
        int stuId = (Integer) map.get("stuId");
        int tutorId = (Integer)map.get("tutorId");
        int useId = tutorMapper.getUserIdByTutorId(tutorId);
        map.put("invitedUserId",useId);
        //type、content、isRead、time
        map.put("type",1);
        map.put("isRead",0);
        map.put("time",DateUtil.currDate());
        //对内容进行处理
        //根据二者id查出对应姓名
        String studentName = studentMapper.getStudentNameById(stuId);
        String tutorName = tutorMapper.getTutorNameById(tutorId);
        String content = "学生"+studentName+"向您的家教"+tutorName+"发出邀请。";
        map.put("content",content);

        return pageTutorMapper.invite(map);
    }

    @Override
    public List<Map<String,Object>> getUserStudent(String userName){
        return pageTutorMapper.getUserStudent(userName);
    }
}
