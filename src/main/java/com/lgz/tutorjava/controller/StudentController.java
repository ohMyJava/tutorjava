package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.service.StudentService;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;


/**
 * @author lgz
 * @date 2020/4/7 16:57
 * 学生控制层
 */
@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    /**
     * 根据前端传来的分页参数实现分页查询
     * @param limit 每页显示条数
     * @param page 当前页数
     * @param condition 模糊查询条件，可为空
     * @return msg
     */
    @GetMapping("getStudents")
    public Message getStudentList(@Param("limit") Integer limit,@Param("page") Integer page,@Param("condition") String condition){
        Message msg=Message.getInstance();
        try {
            //根据分页+模糊查询学生列表，未实现！
            msg.setData(studentService.getStudentList(limit,page,condition));
            LOGGER.info("获取学生列表成功");
            msg.setInfo("6666","查询成功！");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("获取学生列表失败");
            msg.setInfo("7777","查询失败！");
        }
        return msg;
    }

    /**
     * 根据前端传来的学生列表进行批量删除操作
     * @param delList 前端删除学生列表，格式为：（id1，id2，id3，...）
     * @return msg
     */
    @PostMapping("delStudent")
    public Message delStudent(@RequestBody String delList){
        Message msg=Message.getInstance();
        try {
            String list=JsonUtil.jsonToString(delList);
            if (studentService.delStudents(list)>0){
                msg.setInfo("6666","删除成功！");
                LOGGER.info("删除学生列表成功："+delList);
            }else {
                msg.setInfo("7777","删除失败！请重试！");
                LOGGER.info("删除学生列表失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","删除失败！");
            LOGGER.info("删除学生列表操作出现异常"+e.getMessage());
        }
        return msg;
    }

    /**
     * 增加学生
     * @param student 学生对象
     * @return msg
     */
    @PostMapping("addStudent")
    public Message addStudent(@RequestBody Student student){
        Message msg=Message.getInstance();
        try {
            if (studentService.addStudent(student)==1){
                msg.setInfo("6666","添加学生信息成功！");
                LOGGER.info("添加学生成功，姓名为："+student.toString());
            }else {
                msg.setInfo("7777","添加学生失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","添加学生失败！");
            LOGGER.info("添加学生操作出现异常："+e.getMessage());
        }
        return msg;
    }

    /**
     * 根据用户姓名模糊查询，返回其姓名和id
     * @param name 模糊查询条件
     * @return msg
     */
    @GetMapping("studentBindingUser")
    public Message selectStudentBindingUser(String name){
        Message msg=Message.getInstance();
        try {
            Object obj=studentService.getBindingUser(name);
            LOGGER.info("模糊查询用户姓名成功！");
            if (obj!=null){
                msg.setData(obj);
                msg.setInfo("6666","查询成功！");
            }else {
                msg.setInfo("6666","无匹配用户！");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("查询用户名操作出现异常！"+e.getMessage());
            msg.setInfo("7777","查询失败！");
        }
        return msg;
    }

    /**
     * 修改学生信息
     * @param student 学生对象
     * @return msg
     */
    @PostMapping("modifyStudent")
    public Message modifyStudent(@RequestBody Student student){
        Message msg=Message.getInstance();
        try {
            if (studentService.modifyStudent(student)==1){
                msg.setInfo("6666","修改学生信息成功！");
                LOGGER.info("修改学生信息成功！");
            }else {
                msg.setInfo("7777","修改学生信息失败！");
                LOGGER.info("修改学生信息失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","修改学生信息失败！");
            LOGGER.info("修改学生信息操作发生异常！"+e.getMessage());
        }
        return msg;
    }

    /**
     * 修改学生前根据id查询学生信息，用于填充修改学生信息表单中的学生信息。
     * @param studentId 学生id
     * @return msg
     */
    @GetMapping("getOneStudent")
    public Message getOneStudent(Integer studentId){
        Message msg=Message.getInstance();
        try {
            Student student=studentService.getStudent(studentId);
            if (student!=null){
                msg.setData(student);
                msg.setInfo("6666","查询学生信息成功！");
                LOGGER.info("查询学生信息成功！");
            }else {
                msg.setInfo("6666","未找到学生信息！");
                LOGGER.info("未找到学生信息！"+studentId);
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","查询失败！");
            LOGGER.info("查询学生信息操作发生异常！");
        }
        return msg;
    }

    @GetMapping("studentNumber")
    public Message studentNumber(String condition){
        Message msg=Message.getInstance();
        try {
            int total=studentService.studentNumber(condition);
            msg.setData(total);
            msg.setInfo("6666","查询成功！");
            LOGGER.info("查询符合条件学生数量成功"+total);
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","查询失败！");
            LOGGER.info("查询符合条件学生数量失败"+e.getMessage());
        }
        return msg;
    }

}
