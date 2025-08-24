package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member

class FixDiscountPolicy: DiscountPolicy {

    override fun discount(member: Member, price: Int): Int {
        return when (member.grade) {
            Grade.VIP -> {
                DISCOUNT_FIX_AMOUNT
            }

            else -> {
                0
            }
        }
    }

    companion object {
        const val DISCOUNT_FIX_AMOUNT = 1000; // 1000원 할인
    }
}
