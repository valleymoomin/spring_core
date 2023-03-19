package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }


    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입 ->계속 같은걸쓴다.

//        @Autowired
//        ApplicationContext applicationContext;

        @Autowired
//        ObjectProvider<PrototypeBean> prototypeBeanProvider;
        Provider<PrototypeBean> prototypeBeanProvider;

        //필드주입 , (생성자주입해도된다)



//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) { //스프링컨테이너가 prototype만들어서 던져줌
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            return prototypeBean.getCount();

            //int count = prototypeBean.getCount();
            //            return count;
        }
    }

//    @Scope("singleton")
//    static class ClientBean2{
//        private final PrototypeBean prototypeBean; //생성시점에 주입 ->계속 같은걸쓴다. x02
//
////        @Autowired
////        ApplicationContext applicationContext;
//
//        @Autowired
//        public ClientBean2(PrototypeBean prototypeBean) { //스프링컨테이너가 prototype만들어서 던져줌
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic(){
////            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//
//            //int count = prototypeBean.getCount();
//            //            return count;
//        }
//    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this); //this -> 나를찍어줌. 나의 참조값?
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
