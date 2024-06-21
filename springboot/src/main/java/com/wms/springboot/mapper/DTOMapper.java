package com.wms.springboot.mapper;

import com.wms.springboot.entity.Editor;
import com.wms.springboot.entity.RelationDTO;
import com.wms.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface DTOMapper {
    @Insert("INSERT INTO editor(editorname, content, createtime, changetime) " +
            "VALUES(#{editor.editorname}, 'hello welcome to editor', NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "editor.eid", resultType = Integer.class, before = false)
    int insertDtoAndGetId(@Param("editor") Editor editor);
    @Insert("INSERT INTO user_editor_relation (user_id, editor_id) VALUES (#{userId}, #{editorId})")
    void insertRelation(@Param("userId") Integer userId, @Param("editorId") Integer editorId);

    @Insert("insert into `user_editor_relation`(user_id, editor_id)"
            +"values (#{user_id},#{editor_id})")
    void insertDto(RelationDTO editorDto);

    @Select("SELECT uer.id, uer.user_id,uer.editor_id ,uer.editor_id as editor_id_for_join FROM user_editor_relation uer where user_id=#{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "editor_id", column = "editor_id"),
            @Result(property = "editorname", column = "editor_id_for_join", javaType = String.class,
                    one = @One(select = "getEditorNameById"))
    })
    List<RelationDTO> selectById(@Param("userId") Integer userId);

    @Select("SELECT editorname FROM `editor` WHERE eid = #{editorId}")
    String getEditorNameById(@Param("editorId") Integer editorId);

    /*@Select("select * from `editor` order by eid desc ")
    List<RelationDTO> getAll();*/
}
