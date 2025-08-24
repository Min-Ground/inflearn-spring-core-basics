package hello.core.beanfind

import hello.core.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest {

    private val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("모든 빈 출력하기")
    fun findAllBean() {
        for (beanDefinitionName in ac.beanDefinitionNames) {
            val bean = ac.getBean(beanDefinitionName)
            println("name = $beanDefinitionName object = $bean")
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    fun findApplicationBean() {
        for (beanDefinitionName in ac.beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)

            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
            if (beanDefinition.role == BeanDefinition.ROLE_INFRASTRUCTURE) {
                val bean = ac.getBean(beanDefinitionName)
                println("name = $beanDefinitionName object = $bean")
            }
        }
    }
}
