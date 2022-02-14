package games.seac.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/12 - 15:49
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
