package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AgeAnno {
    String value() default "";

    int age() default 1;
}
