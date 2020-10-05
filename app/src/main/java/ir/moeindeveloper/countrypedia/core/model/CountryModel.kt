package ir.moeindeveloper.countrypedia.core.model

import androidx.lifecycle.LiveData
import ir.moeindeveloper.countrypedia.core.intent.Intent
import ir.moeindeveloper.countrypedia.core.state.ViewState
import kotlinx.coroutines.channels.Channel

interface CountryModel<S: ViewState, I: Intent> {
    val intents: Channel<I>
    val state: LiveData<S>
}