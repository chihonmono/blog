package games.seac.blog.dao.pojo;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/9 - 16:41
 */
@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
