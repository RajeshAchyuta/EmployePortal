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

@WebServlet("/UpdateForm")
public class UpdateForm extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String id = request.getParameter("eid");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"Yourpassword");

			PreparedStatement pst = con.prepareStatement(
					"select * from employee where Emp_Id=?");

			pst.setInt(1, Integer.parseInt(id));

			ResultSet rs = pst.executeQuery();

			if(rs.next()) {

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Update Employee</title>");

				out.println("<style>");

				out.println("*{");
				out.println("margin:0;");
				out.println("padding:0;");
				out.println("box-sizing:border-box;");
				out.println("font-family:'Segoe UI',sans-serif;");
				out.println("}");

				out.println("body{");
				out.println("height:100vh;");
				out.println("display:flex;");
				out.println("justify-content:center;");
				out.println("align-items:center;");
				out.println("background:linear-gradient(135deg,#141e30,#243b55);");
				out.println("}");

				out.println(".container{");
				out.println("width:420px;");
				out.println("padding:40px;");
				out.println("border-radius:25px;");
				out.println("background:rgba(255,255,255,0.08);");
				out.println("backdrop-filter:blur(15px);");
				out.println("box-shadow:0 8px 32px rgba(0,0,0,0.4);");
				out.println("color:white;");
				out.println("}");

				out.println("h1{");
				out.println("text-align:center;");
				out.println("margin-bottom:30px;");
				out.println("}");

				out.println(".input-box{");
				out.println("margin-bottom:20px;");
				out.println("}");

				out.println(".input-box label{");
				out.println("display:block;");
				out.println("margin-bottom:8px;");
				out.println("font-weight:bold;");
				out.println("}");

				out.println(".input-box input{");
				out.println("width:100%;");
				out.println("padding:14px;");
				out.println("border:none;");
				out.println("border-radius:10px;");
				out.println("background:rgba(255,255,255,0.15);");
				out.println("color:white;");
				out.println("}");

				out.println(".btn{");
				out.println("width:100%;");
				out.println("padding:14px;");
				out.println("border:none;");
				out.println("border-radius:12px;");
				out.println("background:linear-gradient(45deg,#ff6d00,#ffab00);");
				out.println("color:white;");
				out.println("font-size:16px;");
				out.println("font-weight:bold;");
				out.println("cursor:pointer;");
				out.println("}");

				out.println("</style>");
				out.println("</head>");

				out.println("<body>");

				out.println("<div class='container'>");

				out.println("<h1>Update Employee</h1>");

				out.println("<form action='UpdateDetails' method='post'>");

				out.println("<input type='hidden' name='eid' value='"
						+ rs.getInt(1) + "'>");

				out.println("<div class='input-box'>");
				out.println("<label>Employee Name</label>");
				out.println("<input type='text' name='ename' value='"
						+ rs.getString(2) + "'>");
				out.println("</div>");

				out.println("<div class='input-box'>");
				out.println("<label>Employee Salary</label>");
				out.println("<input type='text' name='esal' value='"
						+ rs.getDouble(3) + "'>");
				out.println("</div>");

				out.println("<div class='input-box'>");
				out.println("<label>Employee Location</label>");
				out.println("<input type='text' name='eloc' value='"
						+ rs.getString(4) + "'>");
				out.println("</div>");

				out.println("<button class='btn'>");
				out.println("✏ Update Employee");
				out.println("</button>");

				out.println("</form>");

				out.println("</div>");

				out.println("</body>");
				out.println("</html>");
			}

			con.close();

		}
		catch(Exception e) {

			out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
