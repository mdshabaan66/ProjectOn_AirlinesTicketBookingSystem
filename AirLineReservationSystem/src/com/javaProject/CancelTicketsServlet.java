package com.javaProject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/CancelTickets")
public class CancelTicketsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/airlinedb";
		String uname = "root";
		String pass = "4290";
		
		String id = request.getParameter("id");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			int count = st.executeUpdate("DELETE from bookingdetail where id='"+id+"'");
			if(count!=0){
				out.println("Your Ticket for booking id "+id+" has been canceled");
			}else
				out.println("You did not book any ticket for booking id "+id+" yet");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Error in Deleting Row");
		}
	}

}
