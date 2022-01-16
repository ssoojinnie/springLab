package kr.co.songjava.framework.data.web.bind.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {

    boolean loginCheck() default false;

    boolean menu() default false;
}
