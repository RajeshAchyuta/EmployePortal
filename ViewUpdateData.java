package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewUpdateData")
public class ViewUpdateData extends HttpServlet {

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
					"Rajesh@1947");

			PreparedStatement pst = con.prepareStatement(
					"select * from employee");

			ResultSet rs = pst.executeQuery();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Update Employees</title>");

			out.println("<style>");

			out.println("*{");
			out.println("margin:0;");
			out.println("padding:0;");
			out.println("box-sizing:border-box;");
			out.println("font-family:'Segoe UI',sans-serif;");
			out.println("}");

			out.println("body{");
			out.println("background:linear-gradient(135deg,#141e30,#243b55);");
			out.println("padding:40px;");
			out.println("color:white;");
			out.println("min-height:100vh;");
			out.println("}");

			out.println("h1{");
			out.println("text-align:center;");
			out.println("margin-bottom:35px;");
			out.println("font-size:38px;");
			out.println("}");

			out.println(".table-container{");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(12px);");
			out.println("border-radius:20px;");
			out.println("padding:20px;");
			out.println("box-shadow:0 8px 32px rgba(0,0,0,0.4);");
			out.println("overflow:auto;");
			out.println("}");

			out.println("table{");
			out.println("width:100%;");
			out.println("border-collapse:collapse;");
			out.println("}");

			out.println("th,td{");
			out.println("padding:16px;");
			out.println("text-align:center;");
			out.println("}");

			out.println("th{");
			out.println("background:linear-gradient(45deg,#ff6d00,#ffab00);");
			out.println("color:white;");
			out.println("font-size:18px;");
			out.println("}");

			out.println("tr{");
			out.println("border-bottom:1px solid rgba(255,255,255,0.2);");
			out.println("}");

			out.println("tr:hover{");
			out.println("background:rgba(255,255,255,0.1);");
			out.println("}");

			out.println("td{");
			out.println("font-size:16px;");
			out.println("}");

			out.println(".btn{");
			out.println("padding:12px 20px;");
			out.println("border:none;");
			out.println("border-radius:10px;");
			out.println("background:linear-gradient(45deg,#ff6d00,#ffab00);");
			out.println("color:white;");
			out.println("font-weight:bold;");
			out.println("cursor:pointer;");
			out.println("transition:0.4s;");
			out.println("}");

			out.println(".btn:hover{");
			out.println("transform:translateY(-3px);");
			out.println("box-shadow:0 0 15px #ff9800;");
			out.println("}");

			out.println(".back{");
			out.println("display:inline-block;");
			out.println("margin-top:30px;");
			out.println("padding:14px 28px;");
			out.println("background:linear-gradient(45deg,#2979ff,#00b0ff);");
			out.println("color:white;");
			out.println("text-decoration:none;");
			out.println("border-radius:12px;");
			out.println("font-weight:bold;");
			out.println("transition:0.4s;");
			out.println("}");

			out.println(".back:hover{");
			out.println("transform:translateY(-4px);");
			out.println("box-shadow:0 0 18px #40c4ff;");
			out.println("}");

			out.println("</style>");
			out.println("</head>");

			out.println("<body>");

			out.println("<h1>Update Employee Records</h1>");

			out.println("<div class='table-container'>");

			out.println("<table>");

			out.println("<tr>");
			out.println("<th>Employee ID</th>");
			out.println("<th>Employee Name</th>");
			out.println("<th>Employee Salary</th>");
			out.println("<th>Employee Location</th>");
			out.println("<th>Action</th>");
			out.println("</tr>");

			while(rs.next()) {

				out.println("<tr>");

				out.println("<td>" + rs.getInt(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getDouble(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td>");

				out.println("<td>");

				out.println("<form action='UpdateForm' method='get'>");

				out.println("<input type='hidden' name='eid' value='"
						+ rs.getInt(1) + "'>");

				out.println("<button class='btn'>");
				out.println("✏ Update");
				out.println("</button>");

				out.println("</form>");

				out.println("</td>");

				out.println("</tr>");
			}

			out.println("</table>");

			out.println("</div>");

			out.println("<a class='back' href='Home.html'>");
			out.println("⬅ Back to Home");
			out.println("</a>");

			out.println("</body>");
			out.println("</html>");

			con.close();

		}
		catch(Exception e) {

			out.println(e);

		}
	}
}