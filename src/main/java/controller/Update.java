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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.UpdateBean;

/**
 *
 * @author kishan
 */
public class Update extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Update</title>");            
            out.println("</head>");
            out.println("<body>");
            Connection con = null;
            int i=0;
            
            try{
            	Class.forName("com.mysql.jdbc.Driver");
           	 con =(Connection)DriverManager.getConnection("jdbc:mysql://localhost/internship","root","2002");
                String id = request.getParameter("sid");
                String name = request.getParameter("sname");
                String gender = request.getParameter("sgender");
                String cgpa = request.getParameter("scgpa");
                if(cgpa==null) {
                	try {
                		 PreparedStatement pstate=con.prepareStatement("update student set sname=? , sgender=? where sid=?");
              			pstate.setString(1, name);
              			pstate.setString(2, gender);
              			pstate.setString(3, id);
              			i=pstate.executeUpdate();
              			if(i>0) {
              				System.out.println("Record Updated.");
              				i=1;
              			}
         		}catch(SQLException e) {
         	                out.println("Error "+e);
         			e.printStackTrace();
         		}
         		finally {
         			try {
         				con.close();
         			} catch (SQLException e) {
         				e.printStackTrace();
         			}
                }
            }
                else if(cgpa!=null) {
                	try {
                	PreparedStatement pstate=con.prepareStatement("update student set sname=? , sgender=? , scgpa=? where sid=?");
        			pstate.setString(1, name);
        			pstate.setString(2, gender);
        			pstate.setString(3, cgpa);
        			pstate.setString(4, id);
        			i=pstate.executeUpdate();
        			if(i>0) {
        				System.out.println("Record Updated.");
        				i=1;
        			}
        		}catch(SQLException e) {
        	                out.println("Error "+e);
        			e.printStackTrace();
        		}
        		finally {
        			try {
        				con.close();
        			} catch (SQLException e) {
        				e.printStackTrace();
        			}
        		}
                }
             if(i==0)
            {
                out.println("<script>alert('Sorry! Invalid ID!')");
                out.println("location='HomePage.jsp';");
                out.println("</script>");
                
            }
             else{
            response.sendRedirect("HomePage.jsp");
             }     
            } catch(SQLException e) {
            	e.printStackTrace();
            }
        PrintWriter out2 = response.getWriter();
            out2.println("</body>");
            out2.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
