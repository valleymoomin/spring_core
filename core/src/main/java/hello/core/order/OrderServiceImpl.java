package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor    //final이 붙은걸 보고 생성자 만들어준다.
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원찾아야함


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //orderservice로직만 해야하는데\
    //자신이 직접 구체적인 fixDiscountpolicy를 선택해버림    , 구체적인 정책까지 직접 생성하고 선택
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;      //구체 객체가 없다. null.discount -> nullpointerException
//final ->기본으로 할당되든 생성자로 할당이 되어야한다. 무조건 값이 있어야한다.
    //생성자에서 들어오는것들은 값이 있어야한다.
    //한번 생성할때 정해지면 안바뀐다. 생성자에서만 값을 넣어줄수있다.
    //초기화 단계에 생성자가 들어와야한다.

    //수정자 이런것들은 의존관계주입할때 2번째단계에서 일어난다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }


    //생성자는 어쩔수없이 빈등록할때 자동주입
    @Autowired //생성자가 1개있으면 @Autowired생략해도 자동주입
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1.OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }  //DIP를 지키게되었당



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //grade를 넘겨도 되고, member를 넘겨도된다.

        //orderService 할인에 대해서 모르고 할인정책 알아해서 해줘 결과만 나에게 던줘줘
        //단일책임의 원칙이 잘 지켜짐.

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
