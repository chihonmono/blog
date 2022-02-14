package games.seac.blog.utils;

import games.seac.blog.dao.pojo.SysUser;

/**
 * @author Honmono
 * @date 2022/2/9 - 16:00
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
