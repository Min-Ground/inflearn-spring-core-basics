package hello.core.order

import hello.core.discount.RateDiscountPolicy
import hello.core.member.MemoryMemberRepository

class OrderServiceImpl : OrderService {

    private val memberRepository = MemoryMemberRepository()
    private val discountPolicy = RateDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}
