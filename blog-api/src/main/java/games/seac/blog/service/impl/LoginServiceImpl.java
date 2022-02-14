package games.seac.blog.service.impl;

import com.alibaba.fastjson.JSON;
import games.seac.blog.dao.pojo.SysUser;
import games.seac.blog.service.LoginService;
import games.seac.blog.service.SysUserService;
import games.seac.blog.utils.JWTUtils;
import games.seac.blog.vo.ErrorCode;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Honmono
 * @date 2022/2/8 - 16:10
 */
@Service
@Transactional //事务
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //加密盐
    private static final String slat = "mszlu!@#";

    @Override
    public SysUser checkToken(String token) {
        /**
         * 1.token合法性校验
         *   是否为空 解析是否为空 redis是否成功
         * 2.如果校验失败 返回错误
         */
        //token为空
        if (StringUtils.isBlank(token)){
            return null;
        }
        //token解析失败
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null){

        }
        //redis中是否过期
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 检查参数是否合法
         * 根据用户名和密码去user表查询 是否存在
         * 如果不存在 登陆失败
         * 如果存在 使用jwt生成token 返回给前端
         * token放入redis中 token：user信息 设置过期时间(登录认证的时候先认证token字符串是否合法，去redis认证是否存在)
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        //合法性判断
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //加密password
        password = DigestUtils.md5Hex(password+slat);
        //查询用户
        SysUser sysUser = sysUserService.findUser(account, password);
        //若用户不存在
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //用户存在
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return Result.success(token);
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    /**
     * 注册
     * @param loginParam
     * @return
     */
    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1.判断参数是否合法
         * 2.判断账户是否存在
         *   存在： 返回账户已注册
         * 3.如果不存在 注册用户
         * 4.生成token
         * 5.存入redis并返回
         * 6.注意加上事务，一旦中间任何过程出现问题，注册的用户需要回滚
         */
        //判断参数是否合法
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        //判断账户是否存在
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户已经被注册");
        }
        //注册用户
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));//需要和登录时的password格式保持一致
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);

        //token
        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }


}
