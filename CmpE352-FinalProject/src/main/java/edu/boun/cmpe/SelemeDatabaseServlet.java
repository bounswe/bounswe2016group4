/** @file SelemeDatabaseServlet.java
 * @author A. Seleme Topuz (slmtpz)
 */


package edu.boun.cmpe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author A. Seleme Topuz
 * Created by A. Seleme Topuz on 10.5.2016.
 */
public class SelemeDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Establishes a database connection via DatabaseAccess class and sends names of video game and engine pairs to database.
     * @param request Request object containing checked ones for submit.
     * @param response Response object.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] ids = request.getParameterValues("VideoGames");
        String nameOfGameEngine = request.getParameter("nameOfGameEngine");
        if (ids != null) {
            for (String id : ids) {
                DatabaseAccess.Insert("VG", id, nameOfGameEngine);
            }
        }

        request.setAttribute("nameOfGameEngine", nameOfGameEngine);
        request.getRequestDispatcher("/seleme.jsp").forward(request, response);
    }
}
