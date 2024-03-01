data class ListItem(val text: String, var isChecked: Boolean = false){

override fun toString(): String {
    return text
    }
}
