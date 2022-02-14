package games.seac.blog.service;

import games.seac.blog.vo.CategoryVo;
import games.seac.blog.vo.Result;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/9 - 16:48
 */
public interface CategoryService {
    /**
     * 查询类别
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询所有类别
     * @return
     */
    Result findAll();

    /**
     *
     * @return
     */
    Result findAllDetail();

    Result categoryDetailById(Long id);

}
