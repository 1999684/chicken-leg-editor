/*
 *功能：
 *日期：2024/6/14 20:21
 *作者：
 */
package com.wms.springboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Editor {
    private Integer eid;
    private String editorname;
    private String content;
    private Date createtime;
    private Date changetime;

    public Integer getId() {
        return eid;
    }

    public void setId(Integer id) {
        this.eid = id;
    }
}
