package hello.core.scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import kotlin.test.Test

class PrototypeProviderTest {

    @Test
    fun providerTest() {
        val ac = AnnotationConfigApplicationContext(ClientBean::class.java, PrototypeBean::class.java)

        val clientBean1 = ac.getBean(ClientBean::class.java)
        val count1 = clientBean1.logic()
        assertThat(count1).isEqualTo(1)

        val clientBean2 = ac.getBean(ClientBean::class.java)
        val count2 = clientBean2.logic()
        assertThat(count2).isEqualTo(1)
    }

    class ClientBean(
        private val ac: ApplicationContext,
    ) {
        fun logic(): Int {
            /**
             * ac.getBean()을 통해 항상 새로운 프로토타입 빈을 생성한다.
             * 의존관계를 외부에서 주입받는 것이 아닌, 필요한 의존관계를 찾는 것을 Dependency Lookup(DL)이라 한다.
             */
            val prototypeBean = ac.getBean(PrototypeBean::class.java)
            prototypeBean.addCount()
            return prototypeBean.getCount()
        }
    }

    @Scope("prototype")
    class PrototypeBean {

        private var count: Int = 0

        fun addCount() = this.count++

        fun getCount() = this.count

        @PostConstruct
        fun init() {
            println("PrototypeBean.init $this")
        }

        @PreDestroy
        fun destroy() {
            println("PrototypeBean.destroy")
        }
    }
}
