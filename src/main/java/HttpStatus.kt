
sealed class HttpStatusOrHttpError

class HttpStatus(var status: Int, var text: String) : HttpStatusOrHttpError() {

    override fun toString(): String {
        return "$status $text"
    }
}

class HttpError() : HttpStatusOrHttpError() {

    override fun toString(): String {
         return "***** Kunde inte ansluta *****"

    }
}