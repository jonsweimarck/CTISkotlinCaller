import java.util.Objects;

public class DocumentMetaData implements Comparable {

    public String documentUrl;
    public String displayName;
    public String documentId;
    public String title;
    public String systemVersion;
    public String businessVersion;
    public String fromDate;
    public String applicationPart;
    public String section;

    public DocumentMetaData(String documentUrl,
                            String displayName,
                            String documentId,
                            String title,
                            String systemVersion,
                            String businessVersion,
                            String fromDate,
                            String applicationPart,
                            String section){
        this.documentUrl = documentUrl;
        this.displayName = displayName;
        this.documentId = documentId;
        this.title = title;
        this.systemVersion = systemVersion;
        this.businessVersion = businessVersion;
        this.fromDate = fromDate;
        this.applicationPart = applicationPart;
        this.section = section;
    }


    @Override
    public int compareTo(Object o) {
        return this.displayName.compareTo(((DocumentMetaData)o).displayName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentMetaData that = (DocumentMetaData) o;
        return documentUrl.equals(that.documentUrl) && displayName.equals(that.displayName) && documentId.equals(that.documentId) && title.equals(that.title) && systemVersion.equals(that.systemVersion) && businessVersion.equals(that.businessVersion) && fromDate.equals(that.fromDate) && Objects.equals(applicationPart, that.applicationPart) && section.equals(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentUrl, displayName, documentId, title, systemVersion, businessVersion, fromDate, applicationPart, section);
    }
}
