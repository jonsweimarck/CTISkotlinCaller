private val baseUrl = "https://euctis-uat.ema.europa.eu/ct-authority-services/services/v1"

fun allDocumentsURL(clinicalTrialId: String, applicationId: String) =
    "$baseUrl/clinicalTrials/$clinicalTrialId/applications/$applicationId/part1/documents?pagesize=30"

fun singleDocumentURL(clinicalTrialId: String, applicationId: String, documentUrl: String) =
    "$baseUrl/clinicalTrials/$clinicalTrialId/applications/$applicationId/part1/documents/$documentUrl"



