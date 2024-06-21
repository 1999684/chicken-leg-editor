/*
 *功能：生成token
 *日期：2024/6/12 9:50
 *作者：
 */
package com.wms.springboot.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wms.springboot.entity.User;
import com.wms.springboot.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostMapping
    public void setUserService(){
        staticUserMapper = userMapper;
    }

    /*
    * 生成token
    *
    * @return token
    * */

    public static  String createToken(String userId,String sign){
        return JWT.create().withAudience(userId)//将user id 保存到token里面，作为载荷       Audience是存储单位，可以存多个数据
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))//2小时后token过期
                .sign(Algorithm.HMAC256(sign));//以password作为token密码
    }

    /*
    * 获取当前登录用户信息
    *
    * @return user对象
    * */


    public static User getCurrentUser(){//只要前端发送了token，就可以根据token知道当前是谁登录
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        }catch (Exception e) {
            return  null;
        }
        return null;
    }





}
