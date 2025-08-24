package hello.core.member

class MemberServiceImpl(
    // 구현 클래스를 해당 클래스에서 주입하지 않고, AppConfig에서 주입한다
    // 구현 클래스의 코드가 더 이상 존재하지 않는다. (의존 X)
    // 인터페이스에 의존함으로써, DIP를 위반하지 않음.
    private val memberRepository: MemberRepository
) : MemberService {

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member {
        return memberRepository.findById(memberId)
    }
}
