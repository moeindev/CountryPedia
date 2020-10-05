package ir.moeindeveloper.countrypedia.core.state


data class CountryViewState<out T>(val status: State,
                                   val data: T? = null,
                                   val Message: String? = null,
                                   val MessageID: Int? = null): ViewState {

    companion object {
        fun <T> success(data: T? = null): CountryViewState<T> {
            return CountryViewState(State.SUCCESS,data)
        }

        fun <T> error(msg: String? = null, msgId: Int? = null, data: T?): CountryViewState<T> {
            return CountryViewState(State.ERROR,data,msg,msgId)
        }

        fun <T> loading(data: T?): CountryViewState<T> {
            return CountryViewState(State.LOADING,data)
        }
    }

}