package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		try {

			/* Load Driver */

			Class.forName("com.mysql.cj.jdbc.Driver");

			/* Create Connection */

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"yourpassword");

			/* Insert Query */

			PreparedStatement pst = con.prepareStatement(
					"insert into users(name,email,password) values(?,?,?)");

			pst.setString(1, name);

			pst.setString(2, email);

			pst.setString(3, password);

			int x = pst.executeUpdate();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration</title>");

			out.println("<style>");

			out.println("body{");
			out.println("height:100vh;");
			out.println("display:flex;");
			out.println("justify-content:center;");
			out.println("align-items:center;");
			out.println("background:linear-gradient(135deg,#141e30,#243b55);");
			out.println("font-family:Segoe UI;");
			out.println("}");

			out.println(".container{");
			out.println("width:400px;");
			out.println("padding:40px;");
			out.println("border-radius:20px;");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(15px);");
			out.println("text-align:center;");
			out.println("color:white;");
			out.println("box-shadow:0 8px 32px rgba(0,0,0,0.4);");
			out.println("}");

			out.println("h1{");
			out.println("margin-bottom:20px;");
			out.println("}");

			out.println(".success{");
			out.println("color:#00e676;");
			out.println("font-size:20px;");
			out.println("font-weight:bold;");
			out.println("margin-bottom:20px;");
			out.println("}");

			out.println(".error{");
			out.println("color:#ff5252;");
			out.println("font-size:18px;");
			out.println("font-weight:bold;");
			out.println("margin-bottom:20px;");
			out.println("}");

			out.println(".btn{");
			out.println("display:inline-block;");
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

			if(x > 0) {

				out.println("<h1>Registration</h1>");

				out.println("<p class='success'>");
				out.println("✅ Registration Successful");
				out.println("</p>");

				out.println("<a class='btn' href='login.html'>");
				out.println("Go To Login");
				out.println("</a>");

			}

			out.println("</div>");

			out.println("</body>");
			out.println("</html>");

			con.close();

		}
		catch(Exception e) {

			if(e.toString().contains("Duplicate")) {

				out.println("<h2 style='color:red;text-align:center;'>");
				out.println("❌ Email already exists");
				out.println("</h2>");

			}
			else {

				out.println("<h2 style='color:red;text-align:center;'>");
				out.println(e);
				out.println("</h2>");
			}
		}
	}
}
