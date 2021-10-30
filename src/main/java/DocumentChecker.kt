
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


    const val user = ""
    const val password = ""

    fun main(args: Array<String>) {
        val ids = ClinicalTrialIdMap.get(ClinicalTrialIdMap.UAT_JW10)
        val restclient = RestClient(user, password)
        val allDocumentsXMLdoc = restclient.getAllDocumentsXML(ids.cliniclaTrialId, ids.applicationId)
        val documentMetaDatas = extractDocumentMetaData(allDocumentsXMLdoc)
        val documentHttpStatuses =
            restclient.checkDocumentHttpStatus(ids.cliniclaTrialId, ids.applicationId, documentMetaDatas)

        println(header(ids))
        println(resultAsString(documentHttpStatuses))
    }

    private fun header(ids: ClinicalTrialIds): String {
        return String.format(
            "\n\n%s\n[%s] Försöker hämta alla dokument för part1 av prövningen '%s' (%s)\n%s",
            "********************************************************************************************************************************",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")),
            ids.name,
            ids.cliniclaTrialId,
            "********************************************************************************************************************************"
        )
    }
