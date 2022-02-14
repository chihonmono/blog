package games.seac.blog.service;

import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.CommentParam;

/**
 * @author Honmono
 * @date 2022/2/11 - 12:46
 */
public interface CommentService {

    /**
     * 根据文章id查询所有评论列表
     * @param id
     * @return
     */
    Result commentByArticleId(Long id);

    Result comment(CommentParam commentParam);
}
