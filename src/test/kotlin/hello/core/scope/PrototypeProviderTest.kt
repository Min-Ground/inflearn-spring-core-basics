package hello.core.scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.ObjectProvider
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
        private val prototypeBeanProvider: ObjectProvider<PrototypeBean>
    ) {
        fun logic(): Int {
            /**
             * 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스 : ObjectProvider
             */
            val prototypeBean = prototypeBeanProvider.getObject()
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
