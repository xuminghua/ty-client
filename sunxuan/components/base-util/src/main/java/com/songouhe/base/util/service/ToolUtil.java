package com.songouhe.base.util.service;

/**
 * @author sunxuan
 * @version 1.0 17-8-8
 */
public class ToolUtil {
    public static boolean existsField(Class clz,String fieldName){
        try{
            return clz.getDeclaredField(fieldName)!=null;
        }
        catch(Exception e){
        }
        if(clz!=Object.class){
            return existsField(clz.getSuperclass(),fieldName);
        }
        return false;
    }

}
