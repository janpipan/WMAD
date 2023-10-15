<%-- 
    Document   : wrapper
    Created on : Oct 15, 2023, 7:48:01 PM
    Author     : entel
--%>

<%@tag description="jsp wrapper tag" pageEncoding="UTF-8" language="java"%>
<%@attribute name="title" required="true" %>
<%@attribute name="content" fragment="true" %>

<!doctype html>
<<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <title>${title}</title>
    </head>
    <body>
        <jsp:doBody/>
    </body>
</html>
