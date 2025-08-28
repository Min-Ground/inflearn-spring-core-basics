package hello.core.lifecycle

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

class NetworkClient : InitializingBean, DisposableBean {

    lateinit var url: String

    init {
        println("생성자 호출")
    }

    fun connect() {
        println("connect : $url")
    }

    fun call(message: String) {
        println("call : $url message : $message")
    }

    fun disconnect() {
        println("close : $url")
    }

    override fun afterPropertiesSet() {
        connect()
        call("초기화 연결 메시지")
    }

    override fun destroy() {
        disconnect()
    }
}
