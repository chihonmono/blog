package games.seac.blog.service;

import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.ArticleParam;
import games.seac.blog.vo.params.PageParams;

/**
 * @author Honmono
 * @date 2022/2/7 - 16:51
 */
public interface ArticleService {
    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @param limit
     * @return
     */
    Result listArchives(int limit);

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);
}
