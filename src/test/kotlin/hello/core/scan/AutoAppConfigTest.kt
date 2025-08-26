package hello.core.scan

import hello.core.AutoAppConfig
import hello.core.member.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {

    @Test
    fun basicBean() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)
        val memberService = ac.getBean(MemberService::class.java)

        assertThat(memberService).isInstanceOf(MemberService::class.java)
    }
}
