/*
 *功能：
 *日期：2024/6/10 21:54
 *作者：
 */
package com.wms.springboot.common;

import lombok.Data;

import java.util.List;

@Data
public class Page <T>{
    private Integer total;
    private List<T> list;
}
