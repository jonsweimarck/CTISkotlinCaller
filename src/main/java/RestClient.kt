
import org.w3c.dom.Document


    private val baseURL = "https://euctis-uat.ema.europa.eu/ct-authority-services/services/v1"

    fun getAllDocumentsXML(
        urlGetter: (String) -> SimpleHttpResponse,
        clinicalTrialId: String,
        applicationId: String): Document {

        val allDocumentsURL = "$baseURL/clinicalTrials/$clinicalTrialId/applications/$applicationId/part1/documents?pagesize=30"
        val response = urlGetter(allDocumentsURL)
        return xmlToDocument(response.body)
    }

    fun checkDocumentHttpStatus(
        urlGetter: (String) -> SimpleHttpResponse,
        clinicalTrialId: String,
        applicationId: String,
        documentMetaDatas: List<DocumentMetaData>
    ): List<DocumentHttpStatus> {

        return documentMetaDatas.map { documentMetaData -> checkSingleDocumentHttpStatus(
                urlGetter,
                clinicalTrialId,
                applicationId,
                documentMetaData
            )
        }
    }

    private fun checkSingleDocumentHttpStatus(
        urlGetter: (String) -> SimpleHttpResponse,
        clinicalTrialId: String,
        applicationId: String,
        documentMetaData: DocumentMetaData
    ): DocumentHttpStatus {
        val singleDocumentURL = "$baseURL/clinicalTrials/$clinicalTrialId/applications/$applicationId/part1/documents/${documentMetaData.documentUrl}"
        val response = urlGetter(singleDocumentURL)
        return DocumentHttpStatus(documentMetaData, response.httpStatus)
    }
