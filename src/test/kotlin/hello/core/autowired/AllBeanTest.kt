package hello.core.autowired

import hello.core.AutoAppConfig
import hello.core.discount.DiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {

    @Test
    fun findAllBean() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)

        val discountService = ac.getBean(DiscountService::class.java)
        val member = Member(1L, "userA", Grade.VIP)

        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        assertThat(discountService).isInstanceOf(DiscountService::class.java)
        assertThat(discountPrice).isEqualTo(1000)
    }

    class DiscountService(
        private val policyMap: Map<String, DiscountPolicy>,
        private val policyList: List<DiscountPolicy>,
    ) {
        fun discount(member: Member, price: Int, discountCode: String): Int {
            val discountPolicy = policyMap[discountCode]
            println("policyMap = $policyMap")
            println("policyList = $policyList")
            println("discountCode = $discountCode")
            println("discountPolicy = $discountPolicy")

            return discountPolicy?.discount(member, price) ?: 0
        }
    }
}
