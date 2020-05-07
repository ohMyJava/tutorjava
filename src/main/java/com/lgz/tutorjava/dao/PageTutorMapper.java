package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Tutor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lgz
 * @date 2020/5/7 8:59
 */
@Repository
public interface PageTutorMapper {
    List<Tutor> getTutors(@Param("able") String able,
                          @Param("school") String school,
                          @Param("location") String location,
                          @Param("page") Integer page,
                          @Param("limit") Integer limit);
    Tutor getOneTutor(Integer tutorId);

    Integer getNumbers(@Param("able") String able,
                       @Param("school") String school,
                       @Param("location") String location);
}
