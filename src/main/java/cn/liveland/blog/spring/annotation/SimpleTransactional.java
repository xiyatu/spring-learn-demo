package cn.liveland.blog.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiyatu
 * @date 2019/7/16 14:46
 * @description 事务注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleTransactional {
}
