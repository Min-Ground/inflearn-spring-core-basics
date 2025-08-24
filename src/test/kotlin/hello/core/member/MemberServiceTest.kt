package hello.core.member

import hello.core.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private val memberService: MemberService = AppConfig().memberService()

    @Test
    fun join() {
        // given
        val member = Member(1L, "MemberA", Grade.VIP)

        // when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then
        Assertions.assertThat(member).isEqualTo(findMember)
    }

}
