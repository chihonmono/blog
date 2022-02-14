package games.seac.blog.controller;

import games.seac.blog.dao.pojo.SysUser;
import games.seac.blog.utils.UserThreadLocal;
import games.seac.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Honmono
 * @date 2022/2/9 - 15:55
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
