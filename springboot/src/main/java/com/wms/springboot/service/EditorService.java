/*
 *功能：
 *日期：2024/6/14 20:30
 *作者：
 */
package com.wms.springboot.service;

import com.wms.springboot.entity.Editor;
import com.wms.springboot.mapper.EditorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorService {
    @Autowired
    EditorMapper editorMapper;
    public void updateEditorContent(Integer eid, String content) {
        // 直接调用 Mapper 方法
        editorMapper.updateEditorContent(eid, content);
    }

    public void addEditor(Editor editor) {editorMapper.insertEditor(editor);}
    public Editor selectById(Integer eid) {
        return editorMapper.selectById(eid);
    }
}
