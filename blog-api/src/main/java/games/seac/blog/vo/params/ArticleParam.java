package games.seac.blog.vo.params;

import games.seac.blog.vo.CategoryVo;
import games.seac.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/11 - 16:21
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}