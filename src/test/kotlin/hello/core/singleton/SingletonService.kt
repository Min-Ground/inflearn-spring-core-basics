package hello.core.singleton

class SingletonService private constructor() {

    fun logic() {
        println("싱글톤 logic 메소드 호출")
    }

    companion object {
        private val instance = SingletonService()

        fun getInstance() = instance
    }
}
