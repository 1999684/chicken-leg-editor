/*
 *功能：具体错误种类分别
 *日期：2024/6/11 8:48
 *作者：
 */
package com.wms.springboot.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final String code;

    public ServiceException(String msg){

        super(msg);
        this.code="500";
    }
    //默认code为500

    public ServiceException(String code,String msg){

        super(msg);
        this.code=code;

    }
    //获取code

}


