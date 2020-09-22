package ir.moeindeveloper.countrypedia.util

fun Int.toStringNumber(): String {
    return java.text.NumberFormat.getIntegerInstance().format(this)
}