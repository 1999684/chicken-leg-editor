/*
 *功能：
 *日期：2024/6/10 18:32
 *作者：
 */
package com.wms.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.wms.springboot.common.Page;
import com.wms.springboot.entity.User;
import com.wms.springboot.exception.ServiceException;
import com.wms.springboot.mapper.UserMapper;
import com.wms.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public  void insertUser(User user){
userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public void batchDeleteUser(List<Integer> ids) {
        for(Integer id:ids){
            userMapper.deleteUser(id);
        }
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public List<User> selectByMore(String username, String name) {
        return userMapper.selectByMore(username,name);
    }

    public List<User> selectByMo(String username, String name) {
        return userMapper.selectByMo(username,name);
    }

    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
        Integer skipNum = (pageNum-1)*pageSize;//计算 1->0,5   2->5,5     3->10,5

        Page<User> page = new Page<>();
        List<User> userList = userMapper.selectByPage(skipNum,pageSize,username,name);
        Integer total = userMapper.selectCountByPage(username,name);
        page.setTotal(total);
        page.setList(userList);

        return page;
    }

    //验证用户账户是否合法
    public User login(User user) {
        //根据用户名查询数据库的用户信息
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if (dbUser == null) {
            //抛出自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {//user前，dbUser后。因为user在webcontroller中检验过，必定不为空
            throw new ServiceException("用户名或密码错误");
        }
        //生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());//传id是用来存到token里面，传password用来验证token
        dbUser.setToken(token);//传出数据

        return dbUser;
    }

    public User register(User user) {
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if(dbUser !=null){
            //抛出自定义的异常
            throw new ServiceException("用户名已经存在");
        }
        user.setName(user.getUsername());
        userMapper.insert(user);
        return user;
    }
}
