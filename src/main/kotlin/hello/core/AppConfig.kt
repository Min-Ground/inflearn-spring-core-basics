package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.member.MemberRepository
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// 객체 생성과 연결(주입)을 담당
@Configuration
class AppConfig {

    @Bean
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    @Bean
    fun memberRepository(): MemberRepository = MemoryMemberRepository()

    @Bean
    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), discountPolicy())

    @Bean
    fun discountPolicy(): DiscountPolicy = FixDiscountPolicy()
}
