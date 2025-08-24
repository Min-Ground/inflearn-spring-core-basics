package hello.core.member

interface MemberRepository {

    fun save(member: Member)

    fun findById(id: Long): Member
}
