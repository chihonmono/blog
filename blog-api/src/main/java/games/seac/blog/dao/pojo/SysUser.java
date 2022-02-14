package games.seac.blog.dao.pojo;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/7 - 16:30
 */
@Data
public class SysUser {
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
