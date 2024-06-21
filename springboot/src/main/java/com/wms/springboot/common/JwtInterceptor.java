/*
 *功能：校验拦截对象
 *日期：2024/6/11 16:37
 *作者：
 */
package com.wms.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wms.springboot.entity.User;
import com.wms.springboot.exception.ServiceException;
import com.wms.springboot.mapper.UserMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");  //header里面传过来的参数
        if (StrUtil.isBlank(token)){
            token = request.getParameter("token");//url参数  ？token=xxx
        }

        //如果不是映射到方法直接通过,表示直接放开方法的健全。只要有AuthAccess注解就不要看下面的认证了
       if (handler instanceof HandlerMethod){
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class); //handler是注解
            if(annotation != null){
                return true;
            }
        }


        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException("401","请登录");
        }
        //获取token的user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);  //JWT.decode(token)解码jwt token     .get(0)拿第一个数据
        }catch (JWTDecodeException j){
            throw new ServiceException("401","请登录");
        }
        //根据token中的user id查询数据库
        User user = userMapper.selectById(Integer.valueOf(userId));
        if(user == null){
            throw new ServiceException("401","请登录");
        }
        //通过用户密码加签生成一个验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();//jwtVerifier是验证器
        try {
            jwtVerifier.verify(token);//验证token
        }catch (JWTVerificationException e){
            throw new ServiceException("401","请登录");
        }
        return true;
    }

}
