package hello.core.scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import kotlin.test.Test

class SingletonWithPrototypeTest1 {

    @Test
    fun protoTypeFind() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        prototypeBean1.addCount()
        assertThat(prototypeBean1.getCount()).isEqualTo(1)

        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)
        prototypeBean2.addCount()
        assertThat(prototypeBean2.getCount()).isEqualTo(1)
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
