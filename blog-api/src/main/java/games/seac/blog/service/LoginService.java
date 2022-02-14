package games.seac.blog.service;

import games.seac.blog.dao.pojo.SysUser;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Honmono
 * @date 2022/2/8 - 16:07
 */
public interface LoginService {

    SysUser checkToken(String token);

    /**
     * 登陆功能
     * @param loginParam
     */
    Result login(LoginParam loginParam);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
