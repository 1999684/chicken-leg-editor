package com.wms.springboot.mapper;

import com.wms.springboot.entity.Editor;
import com.wms.springboot.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EditorMapper {
    @Insert("INSERT INTO editor(editorname, content, createtime, changetime) " +
            "VALUES(#{editorname}, '', NOW(), NOW())")
    int insertEditor(Editor editor);

    @Update("UPDATE editor SET content = #{content} WHERE eid = #{eid}")
    void updateEditorContent(@Param("eid") Integer eid, @Param("content") String content);

    @Select("select * from `editor` where eid =#{eid} order by eid desc ")
    Editor selectById(Integer eid);
}
