import java.util.*

class DocumentMetaData(
    var documentUrl: String,
    var displayName: String,
    var documentId: String,
    var title: String,
    var systemVersion: String,
    var businessVersion: String,
    var fromDate: String,
    var applicationPart: String,
    var section: String
) : Comparable<Any?> {
    override fun compareTo(o: Any?): Int {
        return displayName.compareTo((o as DocumentMetaData?)!!.displayName)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as DocumentMetaData
        return documentUrl == that.documentUrl && displayName == that.displayName && documentId == that.documentId && title == that.title && systemVersion == that.systemVersion && businessVersion == that.businessVersion && fromDate == that.fromDate && applicationPart == that.applicationPart && section == that.section
    }

    override fun hashCode(): Int {
        return Objects.hash(
            documentUrl,
            displayName,
            documentId,
            title,
            systemVersion,
            businessVersion,
            fromDate,
            applicationPart,
            section
        )
    }
}