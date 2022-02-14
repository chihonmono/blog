package games.seac.blog.controller;

import games.seac.blog.utils.QiniuUtils;
import games.seac.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Honmono
 * @date 2022/2/12 - 10:50
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
        //原始文件名称 用户上传时的文件名称
        String originalFilename = file.getOriginalFilename();
        //substringAfterLast() 截取separator参数后面的字符
        //aa.png --> uuid . png
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件 上传到七牛云云储存空间 按量付费 速度快 减少应用服务器的资源消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return Result.success(QiniuUtils.url + "/" + fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}
