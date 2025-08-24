package hello.core.discount

import hello.core.member.Member

interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    fun discount(member: Member, price: Int): Int


}
