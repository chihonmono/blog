package games.seac.blog.service;

import games.seac.blog.dao.pojo.SysUser;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.UserVo;

/**
 * @author Honmono
 * @date 2022/2/7 - 19:05
 */
public interface SysUserService {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
