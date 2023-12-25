package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
        PreparedStatement pstate = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             try{
            	 Class.forName("com.mysql.jdbc.Driver");
            	 con =
            	 (Connection)DriverManager.getConnection("jdbc:mysql://localhost/internship","root","2002");
              String sql="insert into student values (?,?,?,?,?)";
              String sid = request.getParameter("sid");
              String sname = request.getParameter("sname");
              String sgender = request.getParameter("sgender");
              String spass = request.getParameter("sgender");
              String scgpa = null;
              String pass=request.getParameter("password");
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setString(1, sid);
              ps.setString(2, sname);
              ps.setString(3, sgender);
              ps.setString(4, scgpa);
              ps.setString(5, pass);
              ps.executeUpdate();
              con.close();
              response.sendRedirect("index.html");
              
          }catch(Exception e){
              out.println("Error occured : " +e);
          }
        }}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
