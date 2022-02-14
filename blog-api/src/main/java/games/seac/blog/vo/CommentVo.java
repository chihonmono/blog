package games.seac.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/11 - 12:57
 */
@Data
public class CommentVo  {

//    @JsonSerialize(using = ToStringSerializer.class)//解决精度损失问题
    private String id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}