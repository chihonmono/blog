package games.seac.blog.dao.pojo;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/11 - 16:31
 */
@Data
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;
}
