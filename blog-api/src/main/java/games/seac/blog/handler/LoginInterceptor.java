package games.seac.blog.handler;

import com.alibaba.fastjson.JSON;
import games.seac.blog.dao.pojo.SysUser;
import games.seac.blog.service.LoginService;
import games.seac.blog.utils.UserThreadLocal;
import games.seac.blog.vo.ErrorCode;
import games.seac.blog.vo.Result;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Honmono
 * @date 2022/2/9 - 15:15
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法（handler）之前执行
        /**
         * 1.需要判断请求的接口路径是否为HandlerMethod(Controller方法)
         * 2.判断token是否为空 如果为空 未登录
         * 3.如果token不为空 登录验证 loginService.checkToken
         * 4.如果认证成功 放行
         */

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        //判断请求的接口路径是否为HandlerMethod
        if (!(handler instanceof HandlerMethod)){
            //handler可能是访问资源
            return true;
        }
        //判断token是否为空
        String token = request.getHeader("Oauth-Token");

        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (StringUtils.isBlank(token)){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSON(result));
            return false;
        }
        //登录验证
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSON(result));
            return false;
        }
        //登录验证成功 放行
        //我希望再controller中直接获取用户的信息
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除 ThreadLocal中用完的信息 会有内存泄露的奉献
        UserThreadLocal.remove();
    }
}
