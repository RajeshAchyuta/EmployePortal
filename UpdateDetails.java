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

@WebServlet("/UpdateDetails")
public class UpdateDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		/* Get Form Data */

		String id = request.getParameter("eid");
		String name = request.getParameter("ename");
		String sal = request.getParameter("esal");
		String loc = request.getParameter("eloc");

		try {

			/* Load Driver */

			Class.forName("com.mysql.cj.jdbc.Driver");

			/* Create Connection */

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"Yourpassword");

			/* Update Query */

			PreparedStatement pst = con.prepareStatement(
					"update employee set Emp_Name=?, Emp_Sal=?, Emp_Loc=? where Emp_Id=?");

			/* Set Values */

			pst.setString(1, name);

			pst.setDouble(2,
					Double.parseDouble(sal));

			pst.setString(3, loc);

			pst.setInt(4,
					Integer.parseInt(id));

			/* Execute Update */

			int x = pst.executeUpdate();

			/* HTML UI */

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Update Status</title>");

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

			/* Glow Effects */

			out.println("body::before{");
			out.println("content:'';");
			out.println("position:absolute;");
			out.println("width:500px;");
			out.println("height:500px;");
			out.println("background:rgba(0,255,255,0.15);");
			out.println("border-radius:50%;");
			out.println("top:-100px;");
			out.println("left:-100px;");
			out.println("filter:blur(80px);");
			out.println("}");

			out.println("body::after{");
			out.println("content:'';");
			out.println("position:absolute;");
			out.println("width:400px;");
			out.println("height:400px;");
			out.println("background:rgba(255,0,255,0.15);");
			out.println("border-radius:50%;");
			out.println("bottom:-100px;");
			out.println("right:-100px;");
			out.println("filter:blur(80px);");
			out.println("}");

			out.println(".container{");
			out.println("position:relative;");
			out.println("width:420px;");
			out.println("padding:40px;");
			out.println("border-radius:25px;");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(15px);");
			out.println("border:1px solid rgba(255,255,255,0.2);");
			out.println("box-shadow:0 8px 32px rgba(0,0,0,0.4);");
			out.println("text-align:center;");
			out.println("z-index:1;");
			out.println("color:white;");
			out.println("}");

			out.println("h1{");
			out.println("margin-bottom:20px;");
			out.println("font-size:34px;");
			out.println("}");

			out.println(".success{");
			out.println("font-size:20px;");
			out.println("font-weight:bold;");
			out.println("color:#00e676;");
			out.println("margin-bottom:25px;");
			out.println("}");

			out.println(".fail{");
			out.println("font-size:20px;");
			out.println("font-weight:bold;");
			out.println("color:#ff5252;");
			out.println("margin-bottom:25px;");
			out.println("}");

			out.println(".btn{");
			out.println("display:inline-block;");
			out.println("padding:14px 28px;");
			out.println("border:none;");
			out.println("border-radius:12px;");
			out.println("background:linear-gradient(45deg,#2979ff,#00b0ff);");
			out.println("color:white;");
			out.println("font-size:16px;");
			out.println("font-weight:bold;");
			out.println("text-decoration:none;");
			out.println("transition:0.4s;");
			out.println("}");

			out.println(".btn:hover{");
			out.println("transform:translateY(-4px);");
			out.println("box-shadow:0 0 18px #40c4ff;");
			out.println("}");

			out.println("</style>");
			out.println("</head>");

			out.println("<body>");

			out.println("<div class='container'>");

			out.println("<h1>Update Status</h1>");

			if(x > 0) {

				out.println("<p class='success'>");
				out.println("✅ Employee Details Updated Successfully");
				out.println("</p>");

			}
			else {

				out.println("<p class='fail'>");
				out.println("❌ Employee ID Not Found");
				out.println("</p>");
			}

			out.println("<a class='btn' href='Home.html'>");
			out.println("⬅ Back to Home");
			out.println("</a>");

			out.println("</div>");

			out.println("</body>");
			out.println("</html>");

			con.close();

		}
		catch(Exception e) {

			out.println("<h2 style='color:red;'>");
			out.println(e);
			out.println("</h2>");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
