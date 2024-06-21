package com.wms.springboot.controller;


import com.wms.springboot.common.Result;
import com.wms.springboot.entity.Editor;
import com.wms.springboot.entity.RelationDTO;
import com.wms.springboot.entity.User;
import com.wms.springboot.service.DTOservice;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relationDto")
public class RelationDTOController {
    @Autowired
    DTOservice dtoService;

    @PostMapping("/addEditorWithRelation")
    public Result addEditorWithRelation(@RequestBody RelationDTO relationDTO) {
        return dtoService.addEditorWithRelation(relationDTO);
    }

    @PostMapping("/addDto")
    public Result add(@RequestBody RelationDTO relationDTO) {
        dtoService.insertDto(relationDTO);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        List<RelationDTO> dtoList = dtoService.selectById(id);
        return Result.success(dtoList);
    }
}
