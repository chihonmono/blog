package games.seac.blog.controller;

import games.seac.blog.service.LoginService;
import games.seac.blog.service.SysUserService;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Honmono
 * @date 2022/2/8 - 16:05
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录 验证用户
        return loginService.login(loginParam);
    }

}
