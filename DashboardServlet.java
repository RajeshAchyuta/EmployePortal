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

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		try {

			/* Load Driver */

			Class.forName("com.mysql.cj.jdbc.Driver");

			/* Connection */

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbc",
					"root",
					"yourpassowrd");

			/* Total Employees */

			PreparedStatement pst1 = con.prepareStatement(
					"select count(*) from employee");

			ResultSet rs1 = pst1.executeQuery();

			int totalEmployees = 0;

			if(rs1.next()) {

				totalEmployees = rs1.getInt(1);
			}

			/* Highest Salary */

			PreparedStatement pst2 = con.prepareStatement(
					"select max(Emp_Sal) from employee");

			ResultSet rs2 = pst2.executeQuery();

			double maxSalary = 0;

			if(rs2.next()) {

				maxSalary = rs2.getDouble(1);
			}

			/* Lowest Salary */

			PreparedStatement pst3 = con.prepareStatement(
					"select min(Emp_Sal) from employee");

			ResultSet rs3 = pst3.executeQuery();

			double minSalary = 0;

			if(rs3.next()) {

				minSalary = rs3.getDouble(1);
			}

			/* Average Salary */

			PreparedStatement pst4 = con.prepareStatement(
					"select avg(Emp_Sal) from employee");

			ResultSet rs4 = pst4.executeQuery();

			double avgSalary = 0;

			if(rs4.next()) {

				avgSalary = rs4.getDouble(1);
			}

			/* UI */

			out.println("<html>");
			out.println("<head>");

			out.println("<title>Dashboard</title>");

			out.println("<meta charset='UTF-8'>");

			out.println("<meta name='viewport' "
					+ "content='width=device-width, initial-scale=1.0'>");

			out.println("<link rel='stylesheet' "
					+ "href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>");

			out.println("<style>");

			/* Global */

			out.println("*{");
			out.println("margin:0;");
			out.println("padding:0;");
			out.println("box-sizing:border-box;");
			out.println("font-family:'Segoe UI',sans-serif;");
			out.println("}");

			/* Body */

			out.println("body{");
			out.println("min-height:100vh;");
			out.println("background:linear-gradient(135deg,#141e30,#243b55);");
			out.println("overflow-x:hidden;");
			out.println("overflow-y:auto;");
			out.println("padding:40px 20px;");
			out.println("position:relative;");
			out.println("}");

			/* Glow Effects */

			out.println("body::before{");
			out.println("content:'';");
			out.println("position:fixed;");
			out.println("width:500px;");
			out.println("height:500px;");
			out.println("background:rgba(0,255,255,0.12);");
			out.println("border-radius:50%;");
			out.println("top:-150px;");
			out.println("left:-150px;");
			out.println("filter:blur(100px);");
			out.println("z-index:0;");
			out.println("}");

			out.println("body::after{");
			out.println("content:'';");
			out.println("position:fixed;");
			out.println("width:450px;");
			out.println("height:450px;");
			out.println("background:rgba(59,130,246,0.15);");
			out.println("border-radius:50%;");
			out.println("bottom:-150px;");
			out.println("right:-150px;");
			out.println("filter:blur(100px);");
			out.println("z-index:0;");
			out.println("}");

			/* Container */

			out.println(".container{");
			out.println("width:100%;");
			out.println("max-width:1100px;");
			out.println("margin:auto;");
			out.println("position:relative;");
			out.println("z-index:10;");
			out.println("animation:fadeIn 1s ease;");
			out.println("}");

			/* Header */

			out.println(".header{");
			out.println("display:flex;");
			out.println("justify-content:space-between;");
			out.println("align-items:center;");
			out.println("margin-bottom:45px;");
			out.println("flex-wrap:wrap;");
			out.println("gap:20px;");
			out.println("}");

			out.println(".title h1{");
			out.println("font-size:46px;");
			out.println("color:white;");
			out.println("margin-bottom:10px;");
			out.println("}");

			out.println(".title p{");
			out.println("color:#cbd5e1;");
			out.println("font-size:18px;");
			out.println("}");

			/* Back Button */

			out.println(".btn{");
			out.println("display:inline-flex;");
			out.println("align-items:center;");
			out.println("justify-content:center;");
			out.println("gap:10px;");
			out.println("padding:15px 28px;");
			out.println("border-radius:16px;");
			out.println("text-decoration:none;");
			out.println("font-weight:bold;");
			out.println("font-size:16px;");
			out.println("transition:0.4s;");
			out.println("background:linear-gradient(45deg,#2563eb,#00b0ff);");
			out.println("color:white;");
			out.println("box-shadow:0 0 18px rgba(0,176,255,0.25);");
			out.println("}");

			out.println(".btn:hover{");
			out.println("transform:translateY(-5px);");
			out.println("box-shadow:0 0 24px rgba(0,176,255,0.45);");
			out.println("}");

			/* Cards */

			out.println(".cards{");
			out.println("display:grid;");
			out.println("grid-template-columns:repeat(auto-fit,minmax(240px,1fr));");
			out.println("gap:30px;");
			out.println("}");

			/* Card */

			out.println(".card{");
			out.println("padding:35px;");
			out.println("border-radius:28px;");
			out.println("background:rgba(255,255,255,0.08);");
			out.println("backdrop-filter:blur(18px);");
			out.println("border:1px solid rgba(255,255,255,0.12);");
			out.println("box-shadow:0 10px 35px rgba(0,0,0,0.35);");
			out.println("color:white;");
			out.println("transition:0.4s;");
			out.println("text-align:center;");
			out.println("}");

			out.println(".card:hover{");
			out.println("transform:translateY(-8px) scale(1.03);");
			out.println("}");

			/* Icons */

			out.println(".icon{");
			out.println("width:80px;");
			out.println("height:80px;");
			out.println("margin:auto auto 22px;");
			out.println("border-radius:50%;");
			out.println("display:flex;");
			out.println("justify-content:center;");
			out.println("align-items:center;");
			out.println("font-size:34px;");
			out.println("}");

			/* Card Titles */

			out.println(".card h2{");
			out.println("font-size:24px;");
			out.println("margin-bottom:18px;");
			out.println("}");

			/* Values */

			out.println(".card p{");
			out.println("font-size:34px;");
			out.println("font-weight:bold;");
			out.println("}");

			/* Total */

			out.println(".total .icon{");
			out.println("background:rgba(0,230,118,0.18);");
			out.println("color:#00e676;");
			out.println("}");

			out.println(".total{");
			out.println("border-top:5px solid #00e676;");
			out.println("}");

			/* Highest */

			out.println(".max .icon{");
			out.println("background:rgba(64,196,255,0.18);");
			out.println("color:#40c4ff;");
			out.println("}");

			out.println(".max{");
			out.println("border-top:5px solid #40c4ff;");
			out.println("}");

			/* Lowest */

			out.println(".min .icon{");
			out.println("background:rgba(255,171,64,0.18);");
			out.println("color:#ffab40;");
			out.println("}");

			out.println(".min{");
			out.println("border-top:5px solid #ffab40;");
			out.println("}");

			/* Average */

			out.println(".avg .icon{");
			out.println("background:rgba(224,64,251,0.18);");
			out.println("color:#e040fb;");
			out.println("}");

			out.println(".avg{");
			out.println("border-top:5px solid #e040fb;");
			out.println("}");

			/* Animation */

			out.println("@keyframes fadeIn{");

			out.println("from{");
			out.println("opacity:0;");
			out.println("transform:translateY(40px);");
			out.println("}");

			out.println("to{");
			out.println("opacity:1;");
			out.println("transform:translateY(0);");
			out.println("}");

			out.println("}");

			/* Responsive */

			out.println("@media(max-width:700px){");

			out.println(".title h1{");
			out.println("font-size:34px;");
			out.println("}");

			out.println(".title p{");
			out.println("font-size:15px;");
			out.println("}");

			out.println(".card p{");
			out.println("font-size:28px;");
			out.println("}");

			out.println(".header{");
			out.println("justify-content:center;");
			out.println("text-align:center;");
			out.println("}");

			out.println("}");

			out.println("</style>");

			out.println("</head>");

			out.println("<body>");

			out.println("<div class='container'>");

			/* Header */

			out.println("<div class='header'>");

			out.println("<div class='title'>");

			out.println("<h1>📊 Employee Dashboard</h1>");

			out.println("<p>");
			out.println("Real-time employee analytics and statistics");
			out.println("</p>");

			out.println("</div>");

			out.println("<a class='btn' href='Home.html'>");

			out.println("<i class='fa-solid fa-arrow-left'></i>");

			out.println("Back To Home");

			out.println("</a>");

			out.println("</div>");

			/* Cards */

			out.println("<div class='cards'>");

			/* Total Employees */

			out.println("<div class='card total'>");

			out.println("<div class='icon'>");
			out.println("<i class='fa-solid fa-users'></i>");
			out.println("</div>");

			out.println("<h2>Total Employees</h2>");

			out.println("<p>" + totalEmployees + "</p>");

			out.println("</div>");

			/* Highest Salary */

			out.println("<div class='card max'>");

			out.println("<div class='icon'>");
			out.println("<i class='fa-solid fa-arrow-trend-up'></i>");
			out.println("</div>");

			out.println("<h2>Highest Salary</h2>");

			out.println("<p>₹ " + maxSalary + "</p>");

			out.println("</div>");

			/* Lowest Salary */

			out.println("<div class='card min'>");

			out.println("<div class='icon'>");
			out.println("<i class='fa-solid fa-arrow-trend-down'></i>");
			out.println("</div>");

			out.println("<h2>Lowest Salary</h2>");

			out.println("<p>₹ " + minSalary + "</p>");

			out.println("</div>");

			/* Average Salary */

			out.println("<div class='card avg'>");

			out.println("<div class='icon'>");
			out.println("<i class='fa-solid fa-chart-column'></i>");
			out.println("</div>");

			out.println("<h2>Average Salary</h2>");

			out.println("<p>₹ " + avgSalary + "</p>");

			out.println("</div>");

			out.println("</div>");

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
