/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kishan
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
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
    	response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String sname = request.getParameter("username");
            String spass = request.getParameter("password");
            if((sname.equals("admin")) & (spass.equals("admin123"))){
                HttpSession session=request.getSession();
                session.setAttribute("username",sname);
                response.sendRedirect("HomePage.jsp"); 
                System.out.println("Admin loggedin");
            }
            else if(sname!="admin" && spass!="admin123") {
            	int i=0;
            	String sid=null;
            	String sgender=null;
            	String scgpa=null;
            	try {
            		Class.forName("com.mysql.jdbc.Driver");
            		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/internship","root","2002");
            		PreparedStatement pstate=con.prepareStatement("select * from student where sname=? and spass=?");
            		pstate.setString(1,sname);
            		pstate.setString(2,spass);
            		ResultSet rs=pstate.executeQuery();
            		if(rs.next()) {
            			sid=rs.getString(1);
            			sgender=rs.getString(3);
            			scgpa=rs.getString(4);
            			i=1;
            		}
            		}catch(Exception e){
                        out.println("Error occured : " +e);
                    }
            	if(i==1) {
         
            		request.setAttribute("uname", sname);
            		request.setAttribute("id", sid);
            		request.setAttribute("gender", sgender);
            		request.setAttribute("cgpa", scgpa);
            		request.setAttribute("password",spass);
            		request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
            		System.out.println("User logged in");
            	}
            }
            else{
                out.println("<script>alert('Sorry! Invalid Username or Password! Try Again!')</script>");
                RequestDispatcher rd = (RequestDispatcher)request.getRequestDispatcher("/index.html");
                rd.include(request,response);
            }
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
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
