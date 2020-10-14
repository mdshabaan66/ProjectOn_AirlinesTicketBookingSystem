package com.javaProject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

import java.sql.*;
import java.util.Random;


@WebServlet("/ticketsBooking")
public class ticketsBookingServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		 Random random = new Random();
		 //out.println("<LINK REL='StyleSheet' HREF='<%=request.getContextPath()%>/WebContent/style.css' TYPE='text/css'>");
		
		int num = random.nextInt(1000);
		String id = "AIRLINE" + num;
		String name = request.getParameter("name");
		String src = request.getParameter("source");
		String dstn = request.getParameter("destination");
		String date = request.getParameter("date");
		String class_ = request.getParameter("class");
		String passDetail = request.getParameter("detail");
		//num +=1;
		
		out.println("passenger ID: "+ id);
		out.println("passenger Name: "+name);
		out.println("Source: "+ src);
		out.println("Destinination: "+ dstn);
		out.println("Date of Flight: "+ date);
		out.println("Class: "+class_);
		out.println("You are "+ passDetail);
		out.println("\n\n");
		
		out.println("Your ticket has been booked");
		
		try {
			String url = "jdbc:mysql://localhost:3306/airlinedb";
			String uname = "root";
			String pass = "4290";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			
			
			ResultSet rs = st.executeQuery("select * from flightDetail");
			rs.next();
			String time = rs.getString(4);
			int price = rs.getInt(3);
			out.println(time + price);
			if(class_ == "Business")
				price += 1000;
			int count = st.executeUpdate("insert into bookingDetail values('"+ id +"','"+ name +"','"+ src +"','"+ dstn +"','"+ date +"','"+ time +"','"+ class_ +"','"+ passDetail +"',"+ price+ ")");
			out.println(count + "rows affected");
			st.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Error in Connection with database\n");
		}
	}
}

