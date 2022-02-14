package games.seac.blog.controller;

import games.seac.blog.service.CommentService;
import games.seac.blog.vo.Result;
import games.seac.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Honmono
 * @date 2022/2/11 - 12:44
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public Result comment(@PathVariable("id") Long id){
        return commentService.commentByArticleId(id);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentService.comment(commentParam);
    }
}
