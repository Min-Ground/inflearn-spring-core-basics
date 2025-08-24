package hello.core.beanfind

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest {

    private val ac = AnnotationConfigApplicationContext(TestBeanConfig::class.java)

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    fun findAllBeanByParentType() {
        val beansOfType = ac.getBeansOfType(DiscountPolicy::class.java)
        beansOfType.forEach { (key, value) ->
            println("key = $key value = $value")
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    fun findAllBeanByObjectType() {
        val beansOfType = ac.getBeansOfType(Object::class.java)
        beansOfType.forEach { (key, value) ->
            println("key = $key value = $value")
        }
    }

    @Configuration
    class TestBeanConfig {

        @Bean
        fun fixDiscountPolicy(): DiscountPolicy = FixDiscountPolicy()

        @Bean
        fun rateDiscountPolicy(): DiscountPolicy = RateDiscountPolicy()
    }
}
