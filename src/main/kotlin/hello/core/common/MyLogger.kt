package hello.core.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.*

@Component
@Scope(value = "request") // HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다.
class MyLogger {

    var uuid: String? = null
    var requestURL: String? = null

    fun log(message: String) = println("[$uuid]: [$requestURL] $message")

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        println("[$uuid] request scope bean create: $this")
    }

    @PreDestroy
    fun close() {
        println("[$uuid] request scope bean close: $this")
    }
}
