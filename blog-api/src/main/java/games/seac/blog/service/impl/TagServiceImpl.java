package games.seac.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import games.seac.blog.dao.mapper.TagMapper;
import games.seac.blog.dao.pojo.Tag;
import games.seac.blog.service.TagService;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/7 - 18:39
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagByArticleId(Long articleId) {
        //MybatisPlus无法进行多表查询
        List<Tag> tags = tagMapper.findTagByArticleId(articleId);
        return copyList(tags);
    }

    /**
     * 最热标签
     * @param limit
     * @return
     */
    @Override
    public Result hots(int limit) {
        /**
         * 1.标签所拥有的文章数量最多 最热标签
         * 2.查询 根据tag_id 分组 计数 从大到小 排列 取前 limit
         */
        List<Long> tagIds = tagMapper.findHotsByTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        //需求的是 tagId和tagName ——>tag对象
        List<Tag> tagList = tagMapper.findTagByTagIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tagList = this.tagMapper.selectList(queryWrapper);

        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tagList = this.tagMapper.selectList(queryWrapper);

        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }


}
