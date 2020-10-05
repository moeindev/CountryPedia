package ir.moeindeveloper.countrypedia.core.view

import ir.moeindeveloper.countrypedia.core.state.ViewState

interface CountryView<S: ViewState> {

    fun render(state: S)

}