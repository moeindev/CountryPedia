package ir.moeindeveloper.countrypedia.core.intent

sealed class UserIntent: Intent {
    object fetchCountries: UserIntent()
    object realoadCountries: UserIntent()
}