/** @file SelemeServlet.java
 * @author A. Seleme Topuz (slmtpz)
 */

package edu.boun.cmpe;

import org.apache.jena.query.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author A. Seleme Topuz
 * Created by A. Seleme Topuz on 1.5.2016.
 */
public class SelemeServlet extends HttpServlet {

    /**
     * Empty POST method.
     * @param request Request object.
     * @param response Response object.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Pulls a limit of 25 names of games built with the game engine the input game is built.
     * @param request Request object containing search text indicating name of the game.
     * @param response Response object.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText"); ///< searchText implying name of the game to search for.

        //String searchText = "Hearthstone: Heroes of Warcraft";

        String queryString = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
                "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?pLabel ?gLabel WHERE {\n" +
                "?w wdt:P31 wd:Q7889 .\n" +
                "?w wdt:P408 ?p .\n" +
                "?g wdt:P408 ?p .\n" +
                "?w rdfs:label \""+searchText+"\"@en .\n" +
                "?w rdfs:label ?label\n" +
                " SERVICE wikibase:label {\n" +
                " bd:serviceParam wikibase:language \"en\" .\n" +
                "}\n" +
                "} LIMIT 25\n"; ///< Query asking for games built with the game engine with which the input game is built.

        String[] resultArray = {};
        String nameOfGameEngine = "";
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", queryString)) {
            ResultSet results = qexec.execSelect();
            String[] entities = ResultSetFormatter.asText(results).split("\"");
            resultArray = new String[entities.length/4];
            for (int i = 1; i < entities.length; i = i + 2) {
                if (i / 2 % 2 == 0) {
                    nameOfGameEngine = entities[i];
                }
                else {
                    resultArray[i/4] = entities[i];
                }
            }
        } catch (Exception ignored) {

        }

        request.setAttribute("nameOfTheGame", searchText);
        request.setAttribute("nameOfGameEngine", nameOfGameEngine);
        request.setAttribute("resultArray", resultArray);
        request.getRequestDispatcher("/seleme.jsp").forward(request, response);

    }
}
