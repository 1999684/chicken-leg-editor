package com.wms.springboot.entity;
import lombok.Data;

@Data
public class RelationDTO {
    private Integer id;
    private Integer user_id;
    private Integer editor_id;
    private String editorname;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
