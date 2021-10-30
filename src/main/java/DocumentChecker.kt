
import com.mashape.unirest.http.Unirest
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContexts
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.net.ssl.SSLContext


private const val user = ""
private const val password = ""

fun main(args: Array<String>) {
    makeClientIgnoreServerCertificates()

    val ids = ClinicalTrialIdMap.get(ClinicalTrialIdMap.UAT_JW10)

    val urlGetter = urlGetter(user, password)

    val allDocumentsXMLdoc = getAllDocumentsXML(urlGetter, ids.cliniclaTrialId, ids.applicationId)
    val documentMetaDatas = extractDocumentMetaData(allDocumentsXMLdoc)
    val documentHttpStatuses = checkDocumentHttpStatus(urlGetter, ids.cliniclaTrialId, ids.applicationId, documentMetaDatas)

    println(header(ids))
    println(resultAsString(documentHttpStatuses))
}

//private fun urlGetter(user: String, password: String) = { url: String -> Unirest.get(url).basicAuth(user, password).asString()}

private fun urlGetter(user: String, password: String)  = { url: String ->
    val response = Unirest.get(url).basicAuth(user, password).asString()
    SimpleHttpResponse(response.body, HttpStatus(response.status, response.statusText))
}

private fun header(ids: ClinicalTrialIds): String {
    return "\n\n********************************************************************************************************************************\n" +
            "[${LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"))}]" +
            " Försöker hämta alla dokument för part1 av prövningen '${ids.name}' (${ids.cliniclaTrialId})\n" +
            "********************************************************************************************************************************"
}

// This will make Unirest ignore certificates,
// to avoid having to import the server cert to the local java keystore at lib\security\cacerts.
// Taken from one of the answers at https://stackoverflow.com/questions/23242197/how-to-make-unirestjava-ignore-certificate-error
// This is of course not recommended to do ...
private fun makeClientIgnoreServerCertificates() {
    var sslcontext: SSLContext? = null
    try {
        sslcontext = SSLContexts.custom()
            .loadTrustMaterial(null, TrustSelfSignedStrategy())
            .build()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    val sslsf = SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
    val httpclient = HttpClients.custom()
        .setSSLSocketFactory(sslsf)
        .build()
    Unirest.setHttpClient(httpclient)
}

