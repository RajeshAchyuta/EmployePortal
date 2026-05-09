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

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"yourpassword");

			PreparedStatement pst = con.prepareStatement(
					"update users set password=? where email=?");

			pst.setString(1, password);
			pst.setString(2, email);

			int x = pst.executeUpdate();

			if(x > 0) {

			    out.println("<html>");

			    out.println("<head>");

			    out.println("<title>Success</title>");

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
			    out.println("overflow:hidden;");
			    out.println("}");

			    out.println(".box{");
			    out.println("width:420px;");
			    out.println("padding:40px;");
			    out.println("border-radius:28px;");
			    out.println("background:rgba(255,255,255,0.08);");
			    out.println("backdrop-filter:blur(18px);");
			    out.println("border:1px solid rgba(255,255,255,0.12);");
			    out.println("text-align:center;");
			    out.println("color:white;");
			    out.println("box-shadow:0 10px 35px rgba(0,0,0,0.35);");
			    out.println("}");

			    out.println("h1{");
			    out.println("margin-bottom:20px;");
			    out.println("font-size:34px;");
			    out.println("}");

			    out.println("p{");
			    out.println("font-size:18px;");
			    out.println("color:#cbd5e1;");
			    out.println("margin-bottom:30px;");
			    out.println("}");

			    out.println(".btn{");
			    out.println("padding:14px 28px;");
			    out.println("border:none;");
			    out.println("border-radius:14px;");
			    out.println("background:linear-gradient(45deg,#2563eb,#00b0ff);");
			    out.println("color:white;");
			    out.println("font-size:16px;");
			    out.println("font-weight:bold;");
			    out.println("cursor:pointer;");
			    out.println("}");

			    out.println("</style>");

			    out.println("</head>");

			    out.println("<body>");

			    out.println("<div class='box'>");

			    out.println("<h1>✅ Success</h1>");

			    out.println("<p>Password Updated Successfully</p>");

			    out.println("<button class='btn' ");
			    out.println("onclick=\"window.location.href='login.html'\">");

			    out.println("Go To Login");

			    out.println("</button>");

			    out.println("</div>");

			    out.println("</body>");

			    out.println("</html>");
			}
			else {

			    out.println("<h1>Email Not Found</h1>");
			}

			con.close();

		}
		catch(Exception e) {

			out.println(e);
		}
		//out.print("<a href = 'login.html'></a>");
	}
}
