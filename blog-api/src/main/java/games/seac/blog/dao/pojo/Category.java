package games.seac.blog.dao.pojo;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/9 - 16:41
 */
@Data
public class Category {
    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
