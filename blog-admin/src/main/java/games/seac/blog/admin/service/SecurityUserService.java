package games.seac.blog.admin.service;

import games.seac.blog.admin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Honmono
 * @date 2022/2/12 - 16:43
 */
@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录时会把username传递到这里
        //通过username查询admin表 如果admin存在 则将密码告诉springsecurity
        //如果不存在 返回null
        Admin admin = this.adminService.findAdminByUsername(username);
        if (admin == null){
            return null;
        }
        UserDetails userDetails = new User(username,admin.getPassword(),new ArrayList<>());
        return userDetails;
    }
}
