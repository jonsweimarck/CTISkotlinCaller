import java.time.format.DateTimeFormatter;

public class DocumentChecker {

    static final String user = "";
    static final String password = "";

    public static void main(String[] args) throws Exception {

        var ids = ClinicalTrialIdMap.get(ClinicalTrialIdMap.UAT_JW11);

        var restclient = new RestClient(user, password);

        var allDocumentsXMLdoc = restclient.getAllDocumentsXML(ids.getCliniclaTrialId(), ids.getApplicationId());
        var documentMetaDatas = ParserUtil.extractDocumentMetaData(allDocumentsXMLdoc);
        var documentHttpStatuses = restclient.checkDocumentHttpStatus(ids.getCliniclaTrialId(), ids.getApplicationId(), documentMetaDatas);


        System.out.println(header(ids));
        System.out.println(DocumentCheckerResultKt.resultAsString(documentHttpStatuses));
    }

    private static String header(ClinicalTrialIds ids) {

        return String.format("\n\n%s\n[%s] Försöker hämta alla dokument för part1 av prövningen '%s' (%s)\n%s",
                "********************************************************************************************************************************",
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")),
                ids.getName(),
                ids.getCliniclaTrialId(),
                "********************************************************************************************************************************");
    }
}
