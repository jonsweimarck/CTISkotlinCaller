class HttpStatus(var status: Int, var text: String) {
    override fun toString(): String {
        return "$status $text"
    }
}