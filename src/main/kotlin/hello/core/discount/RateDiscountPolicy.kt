package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Qualifier("rateDiscountPolicy")
@Component
class RateDiscountPolicy: DiscountPolicy {

    override fun discount(member: Member, price: Int): Int {
        return when (member.grade) {
            Grade.VIP -> {
                price * DISCOUNT_PERCENT / 100
            }

            else -> {
                0
            }
        }
    }

    companion object {
        const val DISCOUNT_PERCENT = 10
    }
}
