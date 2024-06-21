/*
 *功能：
 *日期：2024/6/14 20:26
 *作者：
 */
package com.wms.springboot.controller;

import com.wms.springboot.common.Result;
import com.wms.springboot.entity.Editor;
import com.wms.springboot.entity.User;
import com.wms.springboot.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    EditorService editorService;

    @PostMapping("/addEditor")
    public Result addEditor(@RequestBody Editor editor) {
        editorService.addEditor(editor);
        return Result.success("添加成功");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEditor(@RequestBody Map<String, Object> requestBody) {
        // 或者创建一个 DTO 来接收 eid 和 content
        Integer eid = Integer.parseInt((String) requestBody.get("eid"));
        String content = (String) requestBody.get("content");

        try {
            editorService.updateEditorContent(eid, content);
            return ResponseEntity.ok("Editor content updated successfully.");
        } catch (Exception e) {
            // 处理异常，例如：NumberFormatException, 自定义业务异常等
            return ResponseEntity.badRequest().body("Failed to update editor content: " + e.getMessage());
        }
    }


    @GetMapping("/selectById/{eid}")
    public Result selectById(@PathVariable Integer eid) {
        Editor editor = editorService.selectById(eid);
        return Result.success(editor);
    }
}
