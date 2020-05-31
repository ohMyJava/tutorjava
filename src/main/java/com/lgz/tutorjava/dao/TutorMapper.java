package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Tutor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/4/23 15:56
 */
@Repository
public interface TutorMapper {


    List<Tutor> getTutors(@Param("limit") Integer limit,
                          @Param("page") Integer page,
                          @Param("condition") String condition);

    int tutorNumber(@Param("condition") String condition);

    int delTutor(@Param("updateTime") String updatetime,
                 @Param("delList") String delList);

    Tutor getOneTutor(Integer tutorId);

    int addTutor(@Param("tutor") Tutor tutor);

    int modifyTutor(@Param("tutor") Tutor tutor);

    String getTutorNameById(Integer tutorId);

    Integer getUserIdByTutorId(Integer tutorId);

    List<Map<String,Object>> getTutorsByUserId(Integer userId);
}
