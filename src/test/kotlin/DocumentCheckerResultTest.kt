
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DocumentCheckerResultTest {

    val header =  "documentType                                                                         docId     documentUrl                             title                                             sysVers        busVers        httpStat  appPart   section   "
    val dummyRow ="documentType                                                                         docId     documentUrl                             title                                             sysVers        busVers        200       appPart   section   "

    @Test
    fun `empty input results in header and empty row`() {
        assertEquals(header + "\n", resultAsString(listOf()))
    }

    @Test
    fun `one entry input results in header and expected single row`() {
        assertEquals(header + "\n" + dummyRow, resultAsString(listOf(dummyDocumentHttpStatus())))
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
            "section",
            "documentFetchUrl"
        )

    private fun dummyDocumentHttpStatus(): Pair<DocumentMetaData, HttpStatus> =
        Pair(dummyDocumentMetaData(), HttpStatus(200, "OK"))
}