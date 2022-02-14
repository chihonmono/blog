package games.seac.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author Honmono
 * @date 2022/2/11 - 17:11
 */
//type代表可以放在类上面 method代表可以放在方法上
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";
    String operator() default "";

}
