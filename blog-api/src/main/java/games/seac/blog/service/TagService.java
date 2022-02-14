package games.seac.blog.service;

import games.seac.blog.vo.Result;
import games.seac.blog.vo.TagVo;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/7 - 18:38
 */
public interface TagService {
    List<TagVo> findTagByArticleId(Long articleId);

    Result hots(int limit);

    Result findAll();

    Result findAllDetail();

    Result findAllDetailById(Long id);
}
