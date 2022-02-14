package games.seac.blog.dao.pojo;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/11 - 12:42
 */
@Data
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}