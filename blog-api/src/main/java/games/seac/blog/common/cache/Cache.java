package games.seac.blog.common.cache;

import java.lang.annotation.*;

/**
 * @author Honmono
 * @date 2022/2/12 - 14:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1 * 60 * 1000;

    String name() default "";

}
