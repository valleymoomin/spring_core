package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest {

        MemberService memberService;
        OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

//    MemberService memberService = new MemberServiceImpl(memberRepository);
//    OrderService orderService = new OrderServiceImpl(discountPolicy, memberRepository);

    @Test
    void createOrder(){
        Long memberId = 1L;     //long member = null; 불가능
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest(){
////        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
//    //nullpointer Exception - > member를 등록해주면된다 중간에 MemberRepostiroyry~
//
//        //임의로 new하는 애들은 autowired가 안된다. 스프링컨테이너에서 가져와야 @Autowired가 먹는거지..
//        //필드인젝션은 하지말자.
//
//
//        orderService.createOrder(1L,"itemA",10000);
//                //repository
//
//    }


}
