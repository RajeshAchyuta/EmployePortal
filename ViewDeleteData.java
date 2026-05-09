package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewDeleteData")
public class ViewDeleteData extends HttpServlet implements Servlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"yourpassword");

			PreparedStatement pst = con.prepareStatement(
					"select * from employee");

			ResultSet rs = pst.executeQuery();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Delete Employees</title>");

			out.println("<style>");

			out.println("body{");
			out.println("font-family:Segoe UI;");
			out.println("background:linear-gradient(135deg,#141e30,#243b55);");
			out.println("padding:40px;");
			out.println("color:white;");
			out.println("}");

			out.println("h1{");
			out.println("text-align:center;");
			out.println("margin-bottom:30px;");
			out.println("}");

			out.println("table{");
			out.println("width:100%;");
			out.println("border-collapse:collapse;");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(10px);");
			out.println("border-radius:15px;");
			out.println("overflow:hidden;");
			out.println("}");

			out.println("th,td{");
			out.println("padding:15px;");
			out.println("text-align:center;");
			out.println("border-bottom:1px solid rgba(255,255,255,0.2);");
			out.println("}");

			out.println("th{");
			out.println("background:#00b0ff;");
			out.println("}");

			out.println("tr:hover{");
			out.println("background:rgba(255,255,255,0.1);");
			out.println("}");

			out.println(".btn{");
			out.println("padding:10px 18px;");
			out.println("border:none;");
			out.println("border-radius:8px;");
			out.println("background:linear-gradient(45deg,#d50000,#ff1744);");
			out.println("color:white;");
			out.println("cursor:pointer;");
			out.println("font-weight:bold;");
			out.println("}");

			out.println(".btn:hover{");
			out.println("box-shadow:0 0 15px #ff5252;");
			out.println("}");

			out.println(".back{");
			out.println("display:inline-block;");
			out.println("margin-top:25px;");
			out.println("padding:12px 25px;");
			out.println("background:#2979ff;");
			out.println("color:white;");
			out.println("text-decoration:none;");
			out.println("border-radius:10px;");
			out.println("}");

			out.println("</style>");
			out.println("</head>");

			out.println("<body>");

			out.println("<h1>Employee Records</h1>");

			out.println("<table>");

			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Salary</th>");
			out.println("<th>Action</th>");
			out.println("</tr>");

			while(rs.next()) {

				out.println("<tr>");

				out.println("<td>" + rs.getInt(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getDouble(3) + "</td>");

				out.println("<td>");

				out.println("<form action='DeleteData' method='post'>");

				out.println("<input type='hidden' name='eid' value='"
						+ rs.getInt(1) + "'>");

				out.println("<form action='DeleteData' method='post'>");

				out.println("<input type='hidden' name='eid' value='"
				        + rs.getInt(1) + "'>");

				out.println("<button class='btn' ");
				out.println("onclick=\"return confirm('Are you sure you want to delete this employee?')\">");

				out.println("Delete");

				out.println("</button>");

				out.println("</form>");

				out.println("</form>");

				out.println("</td>");

				out.println("</tr>");
			}

			out.println("</table>");

			out.println("<a class='back' href='Home.html'>⬅ Back to Home</a>");

			out.println("</body>");
			out.println("</html>");

			con.close();

		}
		catch(Exception e) {
			out.println(e);
		}
	}
}
