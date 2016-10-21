package edu.boun.cmpe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by alp on 5/10/2016.
 */

public class AlptekinDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("DOPOST*******************************");
        String[] ids =  request.getParameterValues("Paintings");
        String Artista =  request.getParameter("Artista");
        System.out.println("DOPOST*******************************");


        if(ids != null ) /// Dont delete it;
        {
            for(String id:ids)
            {
                /**
                 * Getting the checked ones and save them on database.
                 */
                DatabaseAccess.Insert("paintings",id,Artista); // Checked Item TAKEN TO THE DATABSE
            }

        }


        request.setAttribute("Artista", Artista);

        request.getRequestDispatcher("/alptekin.jsp").forward(request, response);

    }
}
