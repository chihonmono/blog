package games.seac.blog.handler;

import games.seac.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author Honmono
 * @date 2022/2/8 - 14:23
 */
//对加了@Controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {
    //进行异常处理
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        //打印堆栈信息
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
