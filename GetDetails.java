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

@WebServlet("/GetDetails")
public class GetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Table Data</title>");

		out.println("<style>");
		out.println("body{");
		out.println("font-family:Arial;");
		out.println("background:linear-gradient(to right,#4facfe,#00f2fe);");
		out.println("margin:0;");
		out.println("padding:0;");
		out.println("display:flex;");
		out.println("justify-content:center;");
		out.println("align-items:center;");
		out.println("height:100vh;");
		out.println("}");

		out.println(".container{");
		out.println("background:white;");
		out.println("padding:30px;");
		out.println("border-radius:15px;");
		out.println("box-shadow:0 0 15px gray;");
		out.println("width:700px;");
		out.println("text-align:center;");
		out.println("}");

		out.println("h2{");
		out.println("color:#333;");
		out.println("margin-bottom:20px;");
		out.println("}");

		out.println("table{");
		out.println("width:100%;");
		out.println("border-collapse:collapse;");
		out.println("margin-top:20px;");
		out.println("}");

		out.println("th,td{");
		out.println("padding:12px;");
		out.println("border:1px solid #ccc;");
		out.println("text-align:center;");
		out.println("}");

		out.println("th{");
		out.println("background:#007bff;");
		out.println("color:white;");
		out.println("}");

		out.println("tr:nth-child(even){");
		out.println("background:#f2f2f2;");
		out.println("}");

		out.println("a{");
		out.println("display:inline-block;");
		out.println("margin-top:20px;");
		out.println("padding:10px 20px;");
		out.println("background:#28a745;");
		out.println("color:white;");
		out.println("text-decoration:none;");
		out.println("border-radius:8px;");
		out.println("}");

		out.println("a:hover{");
		out.println("background:#218838;");
		out.println("}");
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");

		out.println("<div class='container'>");
		out.println("<h2>Rows Retrieved</h2>");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"Rajesh@1947");

			PreparedStatement pst = con.prepareStatement(
					"select * from " + request.getParameter("tname"));

			ResultSet rs = pst.executeQuery();

			out.println("<table>");
			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Salary</th>");
			out.println("</tr>");

			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getDouble(3)+"</td>");
				out.println("</tr>");
			}

			out.println("</table>");

			rs.close();
			pst.close();
			con.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		out.println("<a href='EmoData.html'>Back</a>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}