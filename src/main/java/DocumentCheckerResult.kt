import java.util.stream.Collectors

fun resultAsString (documentHttpStatuses: List<DocumentHttpStatus>) : String {
    val header = formatLine(
                "documentType",
                "docId",
                "documentUrl",
                "title",
                "sysVers",
                "busVers",
                "appPart",
                "section",
                "httpStat")

    val unsortedList = documentHttpStatuses
        .map { entry: DocumentHttpStatus -> formatResultLine(entry) }
        .stream().sorted().collect(Collectors.toList())

    return """
$header
${unsortedList.stream().collect(Collectors.joining("\n"))}
""".trimIndent()
}

private fun formatLine(
    s1: String,
    s2: String,
    s3: String,
    s4: String,
    s5: String,
    s6: String,
    s7: String,
    s8: String,
    s9: String
): String {
    return String.format(
        "%s%s%s%s%s%s%s%s%s",
        padwithSpaces(s1, 85),  // title
        padwithSpaces(s2, 10),  // docId
        padwithSpaces(s3, 40),  // documentUrl
        padwithSpaces(s4, 50),  // title
        padwithSpaces(s5, 15),  // sysVers
        padwithSpaces(s6, 15),  // busVers
        padwithSpaces(s9, 10),  // httpStatus
        padwithSpaces(s7, 10),  // appPart
        padwithSpaces(s8, 10)   // section
    )
}

private fun formatResultLine(dhs: DocumentHttpStatus): String {
    return formatLine(
        dhs.documentMetaData.displayName,
        dhs.documentMetaData.documentId,
        dhs.documentMetaData.documentUrl,
        dhs.documentMetaData.title,
        dhs.documentMetaData.systemVersion,
        dhs.documentMetaData.businessVersion,
        dhs.documentMetaData.applicationPart,
        dhs.documentMetaData.section,
        if(dhs.httpStatusOrHttpError is HttpStatus) dhs.httpStatusOrHttpError.status.toString() else dhs.toString()
    )
}



private fun padwithSpaces(inputString: String, finalLength: Int): String {
    return String.format("%1$-" + finalLength + "s", inputString)
}
