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

@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {

	protected void doPost(HttpServletRequest request,
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
					"YourPassword");

			PreparedStatement pst = con.prepareStatement(
					"delete from employee where Emp_Id=?");

			pst.setInt(1, Integer.parseInt(id));

			int x = pst.executeUpdate();

			if(x > 0) {

				response.sendRedirect("ViewDeleteData");

			}
			else {

				out.println("Delete Failed");

			}

			con.close();

		}
		catch(Exception e) {

			out.println(e);

		}
	}
}
