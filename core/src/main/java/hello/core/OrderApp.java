package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {




//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
//
        //        public OrderService orderService(){ <--[orderService()호출]
        //            return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        //        } //OrderServiceImpl을 반환한다. 근데 생성자로 참조하도록 그림을 완성시키고 완성된 orderserviceImpl객체를반환

//
//        MemberService memberService = new MemberServiceImpl(memberRepository);
//        OrderService orderService = new OrderServiceImpl(null, null);

        //잠깐 null로 했는데 nullpointerException이 발생하는게아님"? <-- [컴파일만 오류가 없으면 된다] 다른클래스실행시


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        //멤버저장
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); //db에 (memory)에 넣어둠 그래야 찾아쓸수있음.

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order); //order.toString이 호출된다.
        System.out.println("order.calculatePrice() = " + order.calculatePrice());


    }
}
