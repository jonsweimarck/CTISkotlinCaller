fun ctisDocumentsFor(
    urlGetter: (String) -> SimpleHttpResponse,
    ids: ClinicalTrialIds): SimpleHttpResponse {

    return urlGetter(allDocumentsURL(ids.cliniclaTrialId, ids.applicationId))
}

fun getHttpStatusForEachDocument(
    urlGetter: (String) -> SimpleHttpResponse,
    documentMetaDatas: List<DocumentMetaData>): List<Pair<DocumentMetaData, HttpStatus>> =

    documentMetaDatas.map { documentMetaData -> checkSingleDocumentHttpStatus(urlGetter, documentMetaData) }

private fun checkSingleDocumentHttpStatus(
    urlGetter: (String) -> SimpleHttpResponse,
    documentMetaData: DocumentMetaData): Pair<DocumentMetaData, HttpStatus> {

    val response = urlGetter(documentMetaData.documentFetchUrl)
    return Pair(documentMetaData, response.httpStatus)
}
