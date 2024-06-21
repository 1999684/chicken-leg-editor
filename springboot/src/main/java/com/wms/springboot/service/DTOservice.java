package com.wms.springboot.service;

import com.wms.springboot.common.Result;
import com.wms.springboot.entity.Editor;
import com.wms.springboot.entity.RelationDTO;
import com.wms.springboot.entity.User;
import com.wms.springboot.mapper.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class DTOservice {
    @Autowired
    DTOMapper dtoMapper;
    public Result addEditorWithRelation(RelationDTO relationDTO) {
        String editorname = relationDTO.getEditorname();
        Integer userId = relationDTO.getUser_id();
        Editor editor = new Editor();
        editor.setEditorname(editorname);
        int editorId = dtoMapper.insertDtoAndGetId(editor);
        if (editorId <= 0) {
            // 处理插入失败的情况
            return Result.error("Failed to insert editor");
        }
        dtoMapper.insertRelation(userId, editor.getId());
        return Result.success("Editor added successfully");
    }

    public void insertDto(RelationDTO relationDTO){
        dtoMapper.insertDto(relationDTO); // 使用 dtoMapper 实例调用方法
    }

    public List<RelationDTO> selectById(Integer userId) {
        return dtoMapper.selectById(userId);
    }
}
