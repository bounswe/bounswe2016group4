package edu.boun.cmpe;

import org.apache.jena.query.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  @author Alptekin Orbay
 *  Backend of the system
 *  WikidataQuery and Database handled here.
 *
 */
public class AlptekinServlet extends HttpServlet {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }


    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchPainting = request.getParameter("SearchPainting");


        /**
         *
         */

        String queryString  = "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
                "PREFIX p: <http://www.wikidata.org/prop/>\n" +
                "PREFIX ps: <http://www.wikidata.org/prop/statement/>\n" +
                "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
                "SELECT ?pLabel ?gLabel WHERE {\n" +
                "                ?w wdt:P31 wd:Q3305213 .\n" +
                "                ?w wdt:P170 ?p .\n" +
                "                ?g wdt:P170 ?p .\n" +
                "                ?w rdfs:label \""+SearchPainting+"\" @en .\n" +
                "                ?w rdfs:label ?label\n" +
                "                SERVICE wikibase:label {\n" +
                "                bd:serviceParam wikibase:language \"en\" \n" +
                "                }\n" +
                "                } LIMIT 10";


        /**
         *
         */
        String[] resultArray = {};
        String Artista = "";
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", queryString)) {
            org.apache.jena.query.ResultSet results = qexec.execSelect();
            String[] entities = ResultSetFormatter.asText(results).split("\"");
            resultArray = new String[entities.length/4];
            if(entities.length > 0)
            Artista = entities[1];


            for (int i = 3; i < entities.length; i = i + 4) {
                    resultArray[i/4] = entities[i];
            }
        } catch (Exception ignored) {


            System.out.println("help MEEEE plsss");
        }


        request.setAttribute("SearchPainting", SearchPainting);
        request.setAttribute("Artista", Artista);
        request.setAttribute("resultArray", resultArray);

        request.getRequestDispatcher("/alptekin.jsp").forward(request, response);

    }
}


