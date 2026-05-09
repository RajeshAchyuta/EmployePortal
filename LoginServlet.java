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
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		try {

			/* Load Driver */

			Class.forName("com.mysql.cj.jdbc.Driver");

			/* Connection */

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"Rajesh@1947");

			/* Select Query */

			PreparedStatement pst = con.prepareStatement(
					"select * from users where email=? and password=?");

			pst.setString(1, email);

			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();

			/* Login Success */

			if(rs.next()) {

				/* Create Session */

				HttpSession session =
						request.getSession();

				session.setAttribute("user",
						email);

				response.sendRedirect("Home.html");

			}
			else {

				/* Login Failed UI */

				out.println("<html>");
				out.println("<head>");

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
				out.println("}");

				out.println(".error{");
				out.println("color:#ff5252;");
				out.println("font-size:22px;");
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

				out.println("<p class='error'>");
				out.println("❌ Invalid Email or Password");
				out.println("</p>");

				out.println("<a class='btn' href='login.html'>");
				out.println("Try Again");
				out.println("</a>");

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
}