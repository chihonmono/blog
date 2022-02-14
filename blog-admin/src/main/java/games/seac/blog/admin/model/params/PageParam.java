package games.seac.blog.admin.model.params;

import lombok.Data;

/**
 * @author Honmono
 * @date 2022/2/12 - 15:42
 */
@Data
public class PageParam {
    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
