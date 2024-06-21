/*创建和返回数据
 *功能：
 *日期：2024/6/10 1:57
 *作者：
 */
package com.wms.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.wms.springboot.common.AuthAccess;
import com.wms.springboot.common.Result;
import com.wms.springboot.entity.User;
import com.wms.springboot.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class WebController {

    @Resource
    UserService userService;

    @AuthAccess
    @GetMapping("/")//    /web/get
    public Result get(String name){
        return Result.success("success");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if (StrUtil.isBlankIfStr(user.getUsername()) || StrUtil.isBlank(user.getPassword())){ //StrUtil.isBlank检查是否为空
            return Result.error("数据输入不合法");
        }
        user = userService.login(user);
        return Result.success(user);
    }

    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if (StrUtil.isBlankIfStr(user.getUsername()) || StrUtil.isBlank(user.getPassword())){ //StrUtil.isBlank检查是否为空
            return Result.error("数据输入不合法");
        }
        if (user.getUsername().length() >10 || user.getPassword().length() > 20){ //StrUtil.isBlank检查是否为空
            return Result.error("数据输入不合法");
        }
        user = userService.register(user);
        return Result.success(user);
    }

}

