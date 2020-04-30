package com.lgz.tutorjava.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/4/15 11:18
 * JSON转换工具类
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * <b>Description:</b> 将json结果集转化为对象<br>
     * <b>Title:</b> jsonToClass<br>
     *
     * @param json
     *            - json数据
     * @param beanType
     *            - 转换的实体类型
     * @return T json转换成对象后的结果（对象类型）
     */
    public static <T> T jsonToClass(String json,Class<T> beanType){
        T t =null;
        try{
            t=mapper.readValue(json,beanType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将json数据转换成list
     * @param json json数据
     * @param beanType
     * @param <T>
     * @return list
     */
    public static <T> List<T> jsonToList(String json,Class<T> beanType){
        List<T> list =null;
        try{
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            list = mapper.readValue(json, javaType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json数据转换成 Map<String,Object>类型的map对象
     * @param json json数据
     * @return map
     */
    public static Map<String,Object> jsonToMap(String json){
        Map<String,Object> map=null;
        try{
            map=mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将对象转换成json数据
     * @param object 任意类型对象
     * @return json数据
     */
    public static String objectToJson(Object object){
        String json=null;
        try {
            json=mapper.writeValueAsString(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 查找json对象数据的属性
     * @param resData 请求的数据(json字符串)
     * @param resPro 请求的属性
     * @return String类型数据
     */
    public static String findValue(String resData,String resPro){
        String result=null;
        try {
            JsonNode node=mapper.readTree(resData);
            JsonNode resProNode=node.get(resPro);
            result=JsonUtil.objectToJson(resProNode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String jsonToString(String json){
        return json.toString();
    }
}
