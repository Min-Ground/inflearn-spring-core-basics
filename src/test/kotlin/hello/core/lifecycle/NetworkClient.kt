package hello.core.lifecycle

class NetworkClient {

    lateinit var url: String

    fun init() {
        println("NetworkClient.init")
        connect()
        call("초기화 연결 메시지")
    }

    fun close() {
        println("NetworkClient.close")
        disconnect()
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
}
