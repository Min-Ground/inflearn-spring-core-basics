package hello.core.member

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private val memberService: MemberService = MemberServiceImpl()

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
