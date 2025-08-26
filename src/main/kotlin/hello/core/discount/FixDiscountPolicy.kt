package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

// @Qualifier의 value는 빈 이름을 바꾸는 게 아니다. 단순 빈을 찾을 떄 사용하는 메타 데이터
@Qualifier("mainDiscountPolicy")
@Component
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
