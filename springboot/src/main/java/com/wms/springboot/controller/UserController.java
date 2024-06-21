/*
 *功能：
 *日期：2024/6/10 18:33
 *作者：
 */
package com.wms.springboot.controller;

import com.wms.springboot.common.Page;
import com.wms.springboot.common.Result;
import com.wms.springboot.entity.User;
import com.wms.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /*
     * 新增信息
     * */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }


    /*
     * 修改用户信息
     * */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }


    /*
     * 单个删除用户信息
     * */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }


    /*
     * 批量删除用户信息
     * */
    @DeleteMapping("/delete/batch")//batch是批量的意思
    public Result batchDelete(@RequestBody List<Integer> ids) {
        userService.batchDeleteUser(ids);
        return Result.success();
    }

    /*
     * 查询全部用户信息
     * */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> userList = userService.selectAll();
        return Result.success(userList);
    }

    /*
     * 根据id查询用户信息
     * */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /*
     * 根据name查询用户信息,如果不确定返回几个就用List
     * */
    @GetMapping("/selectByName/{name}")
    public Result selectByName(@PathVariable String name) {
        List<User> user = userService.selectByName(name);
        return Result.success(user);
    }


    /*
     * 根据name和username查询用户信息
     * 多条件查询信息
     * */
    @GetMapping("/selectByMore")
    public Result selectByMore(@RequestParam String username,@RequestParam String name) {
        List<User> user = userService.selectByMore(username,name);
        return Result.success(user);
    }


    /*
     * 模糊查询用户信息
     * 多条件查询信息
     * */
    @GetMapping("/selectByMo")
    public Result selectByMo(@RequestParam String username,@RequestParam String name) {
        List<User> user = userService.selectByMo(username,name);
        return Result.success(user);
    }

    /*
     * 分页模糊查询用户信息
     * pageNum当前页码
     * */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name) {
        Page<User> page = userService.selectByPage(pageNum, pageSize, username, name);
        return Result.success(page);
    }
}

