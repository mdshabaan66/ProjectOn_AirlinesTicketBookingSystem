package com.javaProject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AvailableFlights")
public class AvailableFlightsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/airlinedb";
		String uname = "root";
		String pass = "4290";
		
		String src = request.getParameter("source");
		String dstn = request.getParameter("destination");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select * from flightDetail where source='"+src+"' and destination='"+dstn+"'");
			out.println("<style type='text/css'>td{padding:20px; border:1px solid black;text-align:center;border-spacing:0px;font-size: 35px;}</style>");
			out.println("<style type='text/css'>table,th{border:1px solid black;border-spacing:0px;font-size: 35px;}</style>");
			out.println("<style type='text/css'>body{background-image: url(images/f1.jpg);\r\n" + 
					"		background-color: #cccccc;\r\n" + 
					"		text-align : center;\r\n" + 
					"		font-size: 35px;}</style>");
			out.println("<body>");
			out.println("<h1 align='center'>Flight Details</h1>");
			out.println("<table style='width:100%'>");
			out.println("<tr>");
			out.println("<th>Source</th> <th>Destination</th> <th>Price</th> <th>Time</th>");
			while(rs.next()) {
				out.println("<tr>");
				//String row = rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4);
				//out.println(row);
				out.println("<td>"+rs.getString(1)+"</td> <td>"+rs.getString(2)+"</td> <td>"+rs.getString(3)+"</td> <td>"+rs.getString(4)+"</td>");
				out.println("</tr>");
			}
			out.println("</table></body>");
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Error");
		}
		
		
		
	}
}
