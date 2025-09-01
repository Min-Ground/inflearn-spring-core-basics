package hello.core.scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import kotlin.test.Test

class SingletonWithPrototypeTest2 {

    @Test
    fun protoTypeFind() {
        val ac = AnnotationConfigApplicationContext(ClientBean::class.java, PrototypeBean::class.java)

        val clientBean1 = ac.getBean(ClientBean::class.java)
        val count1 = clientBean1.logic()
        assertThat(count1).isEqualTo(1)

        val clientBean2 = ac.getBean(ClientBean::class.java)
        val count2 = clientBean2.logic()
        assertThat(count2).isEqualTo(2)
    }

    class ClientBean(
        private val prototypeBean: PrototypeBean,
    ) {
        fun logic(): Int {
            this.prototypeBean.addCount()
            return this.prototypeBean.getCount()
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
