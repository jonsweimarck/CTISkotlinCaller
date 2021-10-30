fun getCtisDocuments(
    urlGetter: (String) -> SimpleHttpResponse,
    clinicalTrialId: String,
    applicationId: String): SimpleHttpResponse {

    return urlGetter(allDocumentsURL(clinicalTrialId, applicationId))
}

fun getHttpStatusForEachDocument(
    urlGetter: (String) -> SimpleHttpResponse,
    documentMetaDatas: List<DocumentMetaData>): List<Pair<DocumentMetaData, HttpStatus>> {

    return documentMetaDatas.map { documentMetaData -> checkSingleDocumentHttpStatus(urlGetter, documentMetaData) }
}

private fun checkSingleDocumentHttpStatus(
    urlGetter: (String) -> SimpleHttpResponse,
    documentMetaData: DocumentMetaData): Pair<DocumentMetaData, HttpStatus> {

    val response = urlGetter(documentMetaData.documentFetchUrl)
    return Pair(documentMetaData, response.httpStatus)
}
