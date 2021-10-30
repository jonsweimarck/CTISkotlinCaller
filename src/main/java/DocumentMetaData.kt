class DocumentMetaData(
    var documentUrl: String,
    var displayName: String,
    var documentId: String,
    var title: String,
    var systemVersion: String,
    var businessVersion: String,
    var fromDate: String,
    var applicationPart: String,
    var section: String,
    val documentFetchUrl: String) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DocumentMetaData

        if (documentUrl != other.documentUrl) return false
        if (displayName != other.displayName) return false
        if (documentId != other.documentId) return false
        if (title != other.title) return false
        if (systemVersion != other.systemVersion) return false
        if (businessVersion != other.businessVersion) return false
        if (fromDate != other.fromDate) return false
        if (applicationPart != other.applicationPart) return false
        if (section != other.section) return false
        if (documentFetchUrl != other.documentFetchUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = documentUrl.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + documentId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + systemVersion.hashCode()
        result = 31 * result + businessVersion.hashCode()
        result = 31 * result + fromDate.hashCode()
        result = 31 * result + applicationPart.hashCode()
        result = 31 * result + section.hashCode()
        result = 31 * result + documentFetchUrl.hashCode()
        return result
    }


}