package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.order.OrderService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)

    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = applicationContext.getBean("memberService", MemberService::class.java)
    val orderService = applicationContext.getBean("orderService", OrderService::class.java)

    val memberId = 1L
    val member = Member(memberId, "MemberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "ItemA", 10000)
    println(order)
}
