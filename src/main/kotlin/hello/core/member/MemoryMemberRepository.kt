package hello.core.member

import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository : MemberRepository {

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(id: Long): Member {
        return store[id] ?: throw NoSuchElementException("Member with id $id not found")
    }

    companion object {
        private val store: MutableMap<Long, Member> = mutableMapOf()
    }
}
