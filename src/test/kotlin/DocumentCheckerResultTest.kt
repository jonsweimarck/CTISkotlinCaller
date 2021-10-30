
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DocumentCheckerResultTest {

    val header =  "documentType                                                                         docId     documentUrl                             title                                             sysVers        busVers        httpStat  appPart   section   "
    val dummyRow ="documentType                                                                         docId     documentUrl                             title                                             sysVers        busVers        200 OK    appPart   section   "

    @Test
    fun `empty input results in header and empty row`() {
        val documentHttpStatuses = listOf<DocumentHttpStatus>()
        val checker = DocumentCheckerResult(documentHttpStatuses)

        assertEquals(header + "\n", checker.result)
    }

    @Test
    fun `one entry input results in header and expected single row`() {
        val checker = DocumentCheckerResult(listOf(dummyDocumentHttpStatus()))

        assertEquals(header + "\n" + dummyRow, checker.result)
    }

    private fun dummyDocumentMetaData(): DocumentMetaData =
        DocumentMetaData(
            "documentUrl",
            "documentType",
            "docId",
            "title",
            "sysVers",
            "busVers",
            "fromDate",
            "appPart",
            "section"
        )

    private fun dummyDocumentHttpStatus(): DocumentHttpStatus =
        DocumentHttpStatus(dummyDocumentMetaData(), HttpStatus(200, "OK"))
}