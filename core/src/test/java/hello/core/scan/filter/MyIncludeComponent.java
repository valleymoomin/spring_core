package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {   //컴포넌트 스캔에 추가할거야 //위@3개 가 중요;어떤뜻?
}