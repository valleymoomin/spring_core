package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan   //클래스찾아서 다 자동스프링빈으로 등록해준다.
        (
                excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

        ) //AppConfig 예제를 안전하게 유지하기 위해서 뺐다.
public class AutoAppConfig {
/*
        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository(){
                return new MemoryMemberRepository();
        }


 */
}
