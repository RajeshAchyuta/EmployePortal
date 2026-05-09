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

@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String id = request.getParameter("eid");

		try {

			/* Load Driver */

			Class.forName("com.mysql.cj.jdbc.Driver");

			/* Connection */

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"Yourpassword");

			/* Search Query */

			PreparedStatement pst = con.prepareStatement(
					"select * from employee where Emp_Id=?");

			pst.setInt(1, Integer.parseInt(id));

			ResultSet rs = pst.executeQuery();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Search Result</title>");

			out.println("<style>");

			out.println("*{");
			out.println("margin:0;");
			out.println("padding:0;");
			out.println("box-sizing:border-box;");
			out.println("font-family:'Segoe UI',sans-serif;");
			out.println("}");

			out.println("body{");
			out.println("min-height:100vh;");
			out.println("display:flex;");
			out.println("justify-content:center;");
			out.println("align-items:center;");
			out.println("background:linear-gradient(135deg,#141e30,#243b55);");
			out.println("padding:20px;");
			out.println("}");

			out.println(".container{");
			out.println("width:550px;");
			out.println("padding:40px;");
			out.println("border-radius:25px;");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(15px);");
			out.println("box-shadow:0 8px 32px rgba(0,0,0,0.4);");
			out.println("color:white;");
			out.println("text-align:center;");
			out.println("}");

			out.println("h1{");
			out.println("margin-bottom:30px;");
			out.println("}");

			out.println("table{");
			out.println("width:100%;");
			out.println("border-collapse:collapse;");
			out.println("margin-top:20px;");
			out.println("}");

			out.println("th,td{");
			out.println("padding:15px;");
			out.println("border:1px solid rgba(255,255,255,0.2);");
			out.println("text-align:center;");
			out.println("}");

			out.println("th{");
			out.println("background:#00b0ff;");
			out.println("}");

			out.println(".error{");
			out.println("color:#ff5252;");
			out.println("font-size:22px;");
			out.println("font-weight:bold;");
			out.println("}");

			out.println(".btn{");
			out.println("display:inline-block;");
			out.println("margin-top:25px;");
			out.println("padding:12px 24px;");
			out.println("background:#00b0ff;");
			out.println("color:white;");
			out.println("text-decoration:none;");
			out.println("border-radius:10px;");
			out.println("font-weight:bold;");
			out.println("}");

			out.println("</style>");

			out.println("</head>");

			out.println("<body>");

			out.println("<div class='container'>");

			out.println("<h1>Search Result</h1>");

			if(rs.next()) {

				out.println("<table>");

				out.println("<tr>");
				out.println("<th>ID</th>");
				out.println("<th>Name</th>");
				out.println("<th>Salary</th>");
				out.println("<th>Location</th>");
				out.println("</tr>");

				out.println("<tr>");

				out.println("<td>" +
						rs.getInt(1) +
						"</td>");

				out.println("<td>" +
						rs.getString(2) +
						"</td>");

				out.println("<td>" +
						rs.getDouble(3) +
						"</td>");

				out.println("<td>" +
						rs.getString(4) +
						"</td>");

				out.println("</tr>");

				out.println("</table>");

			}
			else {

				out.println("<p class='error'>");
				out.println("❌ Employee Not Found");
				out.println("</p>");
			}

			out.println("<a class='btn' href='SearchEmployee.html'>");
			out.println("⬅ Back");
			out.println("</a>");

			out.println("</div>");

			out.println("</body>");
			out.println("</html>");

			con.close();

		}
		catch(Exception e) {

			out.println(e);

		}
	}
}
