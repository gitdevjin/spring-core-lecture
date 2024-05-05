package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor <- this make a constructor with args and autowire it
public class OrderServiceImpl implements OrderService{

    // DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /*@MainDiscountPolicy*/ DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member customer = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(customer, itemPrice);

        return new Order(customer.getId(), itemName, itemPrice, discountPrice);
    }

    //For Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
