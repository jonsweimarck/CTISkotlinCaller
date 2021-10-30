import java.util.stream.Collectors

fun resultAsString (metadatasAndHttpStatuses: List<Pair<DocumentMetaData, HttpStatus>>) : String {
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

    val sortedList = metadatasAndHttpStatuses.map { pair -> formatResultLine(pair) }.sorted()

    return """
$header
${sortedList.stream().collect(Collectors.joining("\n"))}
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

private fun formatResultLine(pair: Pair<DocumentMetaData, HttpStatus>): String {
    return formatLine(
        pair.first.displayName,
        pair.first.documentId,
        pair.first.documentUrl,
        pair.first.title,
        pair.first.systemVersion,
        pair.first.businessVersion,
        pair.first.applicationPart,
        pair.first.section,
        if(pair.second is HttpStatus) pair.second.status.toString() else pair.second.toString()
    )
}



private fun padwithSpaces(inputString: String, finalLength: Int): String {
    return String.format("%1$-" + finalLength + "s", inputString)
}
