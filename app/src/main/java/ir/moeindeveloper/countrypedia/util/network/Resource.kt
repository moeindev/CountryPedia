package ir.moeindeveloper.countrypedia.util.network

data class Resource<out T>(val status: State, val data: T? = null,
                           val Message: String? = null,
                           val MessageID: Int? = null) {

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(State.SUCCESS,data)
        }

        fun <T> error(msg: String? = null, msgId: Int? = null, data: T?): Resource<T> {
            return Resource(State.ERROR,data,msg,msgId)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(State.LOADING,data)
        }
    }

}