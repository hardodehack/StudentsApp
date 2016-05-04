package hardik.studentsApp.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchRegNo extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String SregNo =req.getParameter("regNoI");
		int IregNo = Integer.parseInt(SregNo);
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String url = "jdbc:mysql://localhost:3306/becm45_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(url);
			
			String query = "select * from sinfo where regNo= "+IregNo;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			RequestDispatcher dispatcherh = req.getRequestDispatcher("/header.html");
			dispatcherh.include(req, resp);
			if(rs.next())
			{
			{
				int RegNo = rs.getInt("regNo");
				String FirstName = rs.getString("fname");
				String MiddleName = rs.getString("mname");
				String LastName = rs.getString("lname");
				
				out.println("</br><center><div style=' margin-top:60px;'><table border='1'><tr><td>RegNo</td><td>FirstName</td><td>MiddleName</td><td>LastName</td></tr>"
								+ "<tr><td>"+RegNo+"</td><td>"+FirstName+"</td>"
								+ "<td>"+MiddleName+"</td>"
								+ "<td>"+LastName+"</td></tr></table> </div></center>");
			}	
			}
			else
			{
				out.println("<h1><font color='red'>RegNo "+IregNo+" Not Found </font></h1>");
			}
			
			RequestDispatcher dispatcherf = req.getRequestDispatcher("/footer.html");
			dispatcherf.include(req, resp);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(con!=null)
				{
					con.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
