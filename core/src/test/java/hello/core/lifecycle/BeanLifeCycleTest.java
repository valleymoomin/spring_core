package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //상위 인터페이스 (close쓰기 위함) // 부모는 자식을 담을수있다. ApplicationContext로 close할일이 별로없다.
        //close는 기본인터페이스가 제공해주지않음. 하위까지 내려와야 close까지 쓸수있다. // Configure~이 Annotation보다 부모관계임.
        //Application(최상위부모?) -> Configurable이 그다음부모 -> AnnotationConfig가 자식관계임 여기서
        
        NetworkClient networkClient = ac.getBean(NetworkClient.class); //빈을 조회
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{

//        @Bean(initMethod = "init", destroyMethod = "close") //destroyMethod = "(inferred)"
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev"); //객체 생성한 다음 필요한값을 setter로 넣어줌.
            //setUrl은 NetworkClient가 생성되고 나서 수행되는 로직.
            networkClient.disconnect();
            return networkClient; //스프링빈 생성, 호출된결과물이 등록이된다. 빈이름은 메서드명@

            //라이프사이클 : 스프링 컨테이너생성->스프링 빈생성 -> 의존관계주입 -> 초기화콜백 -> 사용 -> 소멸전콜백->스프링종료
        }
    }
}
