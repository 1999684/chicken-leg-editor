/*
 *功能：定义统一的封装Result  统一返回信息
 *日期：2024/6/10 2:07
 *作者：
 */
package com.wms.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               //自动创造getter和setter方法
@AllArgsConstructor //所有属性的构造函数
@NoArgsConstructor  //无参构造
@Builder            //构建的一种模式
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR= "401";
    public static final String CODE_SYS_ERROR = "500";


    /*
    * 请求的返回编码   200   401   404   500
    *编码表示请求成功或失败
    * 或者 看出错误类型
    * */
    private String code;

    /*
    * msg表示错误的详细信息
    * */
    private String msg;
    /*
    * 数据从什么地方返回出去？
    * 就是data
    * user Objecj类型是User
    * List Object类型是List
    * Map  Object类型就是Map
    * */
    private  Object data;


    //默认的正确返回
    public static Result success(){
        return new Result(CODE_SUCCESS,"请求成功",null);
        //return Result.builder().code(CODE_SUCCESS).msg("请求成功").build();
        //构建者方法
    }

    //带数据的正确返回
    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    //带信息的错误
    public static Result error(String msg){
        return new Result(CODE_SYS_ERROR,msg,null);
    }

    //带code，和信息的错误
    public static Result error(String code, String msg){
        return new Result(code,msg,null);
    }

    //默认错误
    public static Result error(){
        return new Result(CODE_SYS_ERROR,"系统错误",null);
    }
}
