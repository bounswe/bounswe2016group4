package edu.boun.cmpe;
import java.sql.*;
import java.util.*;
/**
 * @author Alptekin Orbay
 * Created by Alptekin Orbay on 5/10/2016.
 * Generic Methods For our team Members
 * This Class is created for handling Database operarions.
 * Important Note : http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm is used to create entire code.
 * Permission :   http://www.tutorialspoint.com/about/faq.htm
 */
public  class DatabaseAccess {

    /**
     *
     * @param TableName Desired Field Name
     * @param Field1 First Field Name
     * @param Field2  Second Field Name
     */
    public static void CreateTable(String TableName, String Field1, String Field2) //** For creating a two field table
    {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/Data";
        String USER = "root";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String Query = " create table "+  TableName+ "(" + Field1 + " varchar(100)," + Field2 + "  varchar(100));";
            System.out.println(Query);
            stmt.executeUpdate(Query);
            stmt.close();
            conn.close();



        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

    }



    public static  Vector<String> Show(String Table,String Field1,String Field2) //For obtaining records
    {   String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/Data";
        String USER = "root";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        Vector<String> CurrentDatabase  = new Vector<String>() ;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String Query = " select * from " + Table ;
            System.out.println(Query+ "**********************************************************");
            ResultSet resu =  stmt.executeQuery(Query);

            while(resu.next()){
                //Retrieve by column name

                String f1 = resu.getString(Field1);
                String f2 = resu.getString(Field2);
                CurrentDatabase.add(f1);
                CurrentDatabase.add(f2);
            }

            resu.close();
            stmt.close();
            conn.close();



        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

         return CurrentDatabase ;
    }


    /**
     *
     * @param Table Select your own table for records
     * @param Field1 First field for your record
     * @param Field2 Second field
     */

    public static void Insert(String Table,String Field1,String Field2 ) // !!!recorc must be size of 2!!!!
    {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/Data"; // Our Database

        //  Database credentials
        String USER = "root";
        String PASS = ""; // It will change  to the servers mysql password


        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement---////");
            stmt = conn.createStatement();
            String sql;
            sql = " insert into "+ Table +"   values (\""+ Field1 + "\",\""+Field2 + "\");";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
