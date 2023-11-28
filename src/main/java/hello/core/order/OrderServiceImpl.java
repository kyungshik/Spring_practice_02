package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //생성자 주입 : 생성자 주입을 통해서 의존관계를 설정한다. 불편, 필수, 의존관계일 때 사용된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //private final : 꼭 값이 있어야 한다.

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    //수정자 의존관계
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//        스프링 컨테이너에서 스프링 빈을 꺼낸다. MemberRepository, DiscountPolicy를 불러온다
//     @Autowired
//    생성자가 1개 있다면, @Autowired를 생략해도 된다.
//     11/24 : Lombok @RequiredArgsConstructor 를 통해서 생성자를 만들 필요가 없다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//        //private final : 값 설정 (this.memberRepository) 없으면 오류 발생
//    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
