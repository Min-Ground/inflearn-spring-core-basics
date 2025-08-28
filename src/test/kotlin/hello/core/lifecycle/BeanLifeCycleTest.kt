package hello.core.lifecycle

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.test.Test

class BeanLifeCycleTest {

    @Test
    fun lifeCycleTest() {
        val ac = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        val networkClient = ac.getBean(NetworkClient::class.java)
        ac.close() // 스프링 컨테이너 종료
    }

    @Configuration
    class LifeCycleConfig {

        @Bean
        fun networkClient(): NetworkClient {
            val networkClient = NetworkClient()
            networkClient.url = "http://127.0.0.1:8080"
            return networkClient
        }
    }
}
