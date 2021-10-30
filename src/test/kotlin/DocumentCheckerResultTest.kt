
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DocumentCheckerResultTest {

    val header = "documentType                                                                         docId     documentUrl                             title                                             sysVers        busVers        httpStat  appPart   section   "


    @Test
    fun `empty input results in header and empty row`() {
        val documentHttpStatuses = listOf<DocumentHttpStatus>()
        val checker = DocumentCheckerResult(documentHttpStatuses)

        assertEquals(header + "\n", checker.result)
    }

//    @Test
//    fun `one entry input results in header and expected single row`() {
//        val documentMetaDatas = listOf(dummyDocumentMetaData())
//        val documentHttpStatuses = listOf<Optional<DocumentHttpStatus>>(dummyHttpStatus())
//        val checker = DocumentCheckerResult(documentMetaDatas, documentHttpStatuses)
//
//        assertEquals(header + "\n", checker.result)
//    }
//
//    private fun dummyDocumentMetaData(): DocumentMetaData =
//        DocumentMetaData(
//            "documentUrl",
//            "displayName",
//            "documentId",
//            "tittle",
//            "systemVersion",
//            "businessVersion",
//            "fromDate",
//            "applicationPart",
//            "section"
//        )
//
//    private fun dummyHttpStatus(): <Optional<DocumentHttpStatus>> =
//    DocumentHttpStatus(
//
//        )
}