package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.member.MemberRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    @Qualifier("mainDiscountPolicy") private val discountPolicy: DiscountPolicy,
) : OrderService {

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}
