/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katona Vera
 */
@WebServlet(name = "AreaServlet", urlPatterns = {"/Areas"})
public class AreaServlet extends HttpServlet {
private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/empire";
    private static final String TABLE_NAME = "areas";
    Connection conn = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AreaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AreaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String area = request.getParameter("area");
        String description = request.getParameter("description");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            
            System.out.println("Connected.");
            Statement statement = (Statement) conn.createStatement();           
            String insertStatement = "insert into " + "area" + " (area_name, description) values ('" + area + "','" + description + "')";
            statement.execute(insertStatement);
            response.getWriter().print(area + " added successfuly");
        } catch (SQLException e) {
            Logger.getLogger(AreaServlet.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AreaServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
