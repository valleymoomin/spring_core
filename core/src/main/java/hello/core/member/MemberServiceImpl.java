package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //join , findMember 하려면 repository가 필요하고 null을 막기위해 구현체를 선택해준다.

    private final MemberRepository memberRepository;

    @Autowired      //memberRepository에 타입에 맞는 memorymemberRepository를 등록해준다.
    //마치 ac.getBean(MemberRepository.class)처럼 작동한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //MemberRepository 구현체가 무엇이 들어갈지를 <생성자>를 통해서 지정해준다.
    //추상화에만 의존

    @Override
    public void join(Member member) {
        memberRepository.save(member); //다형성에 의해서 MemoryMemberRepository의 save()가 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도.
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
