<%@ page import="edu.boun.cmpe.DatabaseAccess" %>
<%@ page import="java.util.Vector" %><%--
  Created by IntelliJ IDEA.
  User: A. Seleme Topuz
  Date: 1.5.2016
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Assignment 6 - Seleme</title>
</head>
<body bgcolor="DarkSalmon">
<p>This is the assignment 6 page of <a href="https://github.com/bounswe/bounswe2016group4/wiki/Seleme-Topuz">Seleme Topuz</a></p> <br>

<p>Input name of a video game into the search box. <br>
    The system will find games built with the same game engine with which the input game is built. <br>
    The search process will be done via wikidata.org with SPARQL queries.</p> <br>
<p>Note: Due to process being handled over query.wikidata.org, it may took some time to give result. (~30 secs max.)</p>

<%
    String[] resultArray = (String[]) request.getAttribute("resultArray");
    String nameOfGameEngine = (String) request.getAttribute("nameOfGameEngine");
    String nameOfTheGame = (String) request.getAttribute("nameOfTheGame");
    if (nameOfTheGame == null) nameOfTheGame = "Temple Run";

    out.print("<form id=\"semanticSearch\" method=\"GET\" action=\"selemeServlet\">");
    out.print("<input type=\"text\" title=\"Search Text\" name=\"searchText\" size=\"35\" value=\"" + nameOfTheGame + "\">");
    out.print("<input type=\"submit\" value=\"Search\">");
    out.print("</form>");

    if (resultArray != null) {
        out.print("<p><b> " + nameOfGameEngine + " </b> is the game engine with which <b> " + nameOfTheGame + " </b> is built.</p>");
        out.print("<p> These are the games built with the game engine <b> " + nameOfGameEngine + "</b></p><br>");


        out.print("<form id=\"addSelectedToDatabase\" method=\"GET\" action=\"selemeDatabaseAction\"> <br>");
        out.print("<input type=\"text\" name=\"nameOfGameEngine\" value=\"" + nameOfGameEngine + "\" readonly> <br>");
        for (int i = 0; i < resultArray.length; i++) {
            out.print("<input type=\"checkbox\" name=\"VideoGames\" value=\"" + resultArray[i] + "\">" + resultArray[i] + "<br>");
        }
        out.print("<input type=\"submit\" value=\"Submit\">");
        out.print("</form>");
    } else {
        Vector elements = DatabaseAccess.Show("VG", "VGN", "Engine");
        out.print("<p> These are the game/game engine pairs in the database. </p><br>");

        out.print("<table style=\"width:30%\">");
        out.print("<tr>");
        out.print("<td> <b>Game</b> </td> <td> <b>Game Engine</b></td>");
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
