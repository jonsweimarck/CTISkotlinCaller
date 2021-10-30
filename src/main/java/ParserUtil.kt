import org.w3c.dom.Document
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory


fun xmlToDocument(xmlString: String?): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val doc = db.parse(InputSource(StringReader(xmlString)))
        doc.documentElement.normalize()
        return doc
    }

    fun extractDocumentMetaData(allDocumentsXMLdoc: Document): List<DocumentMetaData> {
        val returnList: MutableList<DocumentMetaData> = ArrayList()
        val nList = allDocumentsXMLdoc.getElementsByTagName("document")
        for (i in 0 until nList.length) {
            val nNode = nList.item(i)
            val eElement = nNode as Element
            val documentUrlElement = eElement.getElementsByTagName("documentUrl").item(0) as Element
            val documentIdElement = eElement.getElementsByTagName("documentId").item(0) as Element
            val documentTitleElement = eElement.getElementsByTagName("title").item(0) as Element
            val documentSysVersionElement = eElement.getElementsByTagName("systemVersion").item(0) as Element
            val documentBusVersionElement = eElement.getElementsByTagName("businessVersion").item(0) as Element
            val documentFromDateElement = eElement.getElementsByTagName("fromDate").item(0) as Element
            val documentApplicationPartElement = if (eElement.getElementsByTagName("applicationPart")
                    .item(0) != null
            ) eElement.getElementsByTagName("applicationPart").item(0).textContent else "null"
            val documentSectionElement =
                if (eElement.getElementsByTagName("section").item(0) != null) eElement.getElementsByTagName("section")
                    .item(0).textContent else "null"
            val documentTypeCodeNodeList = eElement.getElementsByTagName("documentTypeCode")
            val documentTypeCode = documentTypeCodeNodeList.item(0) as Element
            val displayNameElement = documentTypeCode.getElementsByTagName("displayName").item(0) as Element
            returnList.add(
                DocumentMetaData(
                    documentUrlElement.textContent,
                    displayNameElement.textContent,
                    documentIdElement.textContent,
                    documentTitleElement.textContent,
                    documentSysVersionElement.textContent,
                    documentBusVersionElement.textContent,
                    documentFromDateElement.textContent,
                    documentApplicationPartElement,
                    documentSectionElement
                )
            )
        }
        return returnList
    }
