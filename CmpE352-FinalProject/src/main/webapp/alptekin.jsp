<%@ page import="edu.boun.cmpe.DatabaseAccess" %>
<%@ page import="java.util.Vector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Assignment 6 - Alptekin</title>
</head>
<body bgcolor="yellow">
<p>This is the assignment 6 page of <a href="https://github.com/bounswe/bounswe2016group4/wiki/Alptekin-Orbay">Alptekin</a></p> <br>

<p>Welcome Painting Lover <br>
   Enter your favorite painting, I will give you other paintings of yours creator.   <br>
    It will take some time , There is lots of paitings :) </p> <br>
<%
    String SearchPainting = (String) request.getAttribute("SearchPainting");
    if ( SearchPainting == null) SearchPainting = "Orange";
    out.print("<form id=\"semanticSearch\" method=\"GET\" action=\"alp\">");
    out.print("<input type=\"text\" title=\"Search Painting\" name=\"SearchPainting\" size=\"50\" value=\"" + SearchPainting + "\">");
    out.print("<input type=\"submit\" value=\"Search\">");
    String[] resultArray = (String[]) request.getAttribute("resultArray");
    String Artista = (String) request.getAttribute("Artista");
    out.print("</form>");

    if (resultArray != null)
    {

        out.print("<form id=\"addSelectedToDatabase\" method=\"GET\" action=\"alptekinDatabaseAction\"> <br>");
        out.print("<input type=\"text\" name=\"Artista\" value=\""+Artista+"\" readonly> <br>");
        for (int i = 0; i < resultArray.length; i++)
        {
            out.print("<input type=\"checkbox\" name=\"Paintings\" value=\"" + resultArray[i] + "\">" + resultArray[i] + "<br>");
        }
        out.print("<input type=\"submit\" value=\"Submit\">");
        out.print("</form>");
    } else {
        Vector elements = DatabaseAccess.Show("paintings", "Name", "Artist");
        out.print("<p> These are the painting/painter pairs in the database. </p><br>");

        out.print("<table style=\"width:30%\">");
        out.print("<tr>");
        out.print("<td><b> Painting </b></td> <td> <b>Painter</b></td>");
        out.print("</tr>");
        for (int i = 0; i < elements.size(); i=i+2) {
            out.print("<tr>");
            out.print("<td>"+elements.elementAt(i)+"</td> <td>"+elements.elementAt(i+1)+"</td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }


%>

</body>
</html>
