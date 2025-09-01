package hello.core.singleton

import hello.core.AppConfig
import hello.core.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    fun singletonServiceTest() {
        val singletonService1: SingletonService = SingletonService.getInstance()
        val singletonService2: SingletonService = SingletonService.getInstance()

        Assertions.assertThat(singletonService1).isSameAs(singletonService2)
    }

    @Test
    @DisplayName("순수한 DI 컨테이너")
    fun pureContainerTest() {
        val appConfig = AppConfig()

        val memberService1 = appConfig.memberService()
        val memberService2 = appConfig.memberService()

        Assertions.assertThat(memberService1).isNotSameAs(memberService2)
    }

    @Test
    @DisplayName("스프링 DI 컨테이너")
    fun springContainerTest() {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService1 = ac.getBean("memberService", MemberService::class.java)
        val memberService2 = ac.getBean("memberService", MemberService::class.java)

        Assertions.assertThat(memberService1).isSameAs(memberService2)
    }
}
