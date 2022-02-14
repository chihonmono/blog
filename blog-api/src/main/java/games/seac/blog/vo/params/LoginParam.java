package games.seac.blog.vo.params;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/8 - 16:09
 */
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
