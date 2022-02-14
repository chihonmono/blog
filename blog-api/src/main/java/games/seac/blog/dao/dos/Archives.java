package games.seac.blog.dao.dos;

import lombok.Data;

/**
 * do 也是从数据库中查询出来 但不需要实例化的对象
 * @author Honmono
 * @date 2022/2/8 - 15:09
 */
@Data
public class Archives {
    private Integer year;

    private Integer month;

    private Long count;

}
