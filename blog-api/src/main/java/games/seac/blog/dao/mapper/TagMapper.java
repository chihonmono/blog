package games.seac.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.seac.blog.dao.pojo.Tag;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/7 - 16:30
 */
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     *
     * @param articleId
     * @return
     */
    List<Tag> findTagByArticleId(Long articleId);

    /**
     * 查询最热标签 前n条
     * @param limit
     * @return
     */
    List<Long> findHotsByTagIds(int limit);

    List<Tag> findTagByTagIds(List<Long> tagIds);
}
