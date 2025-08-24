package hello.core.order

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private val memberService = MemberServiceImpl()
    private val orderService = OrderServiceImpl()

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(1L, "MemberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "ItemA", 10000)
        Assertions.assertThat(order.discountPrice).isEqualTo(1000)
    }
}
