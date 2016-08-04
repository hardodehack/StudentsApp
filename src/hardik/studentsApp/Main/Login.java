package hardik.studentsApp.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String IregNo = req.getParameter("regNoI");
		String Ipass = req.getParameter("passI");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			/*out.println(Ifname+Ipassword);*/
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String DBurl = "jdbc:mysql://localhost:3306/becm45_db?user=j2ee&password=j2ee";
			con=DriverManager.getConnection(DBurl);
			
			String query = "select * from soinfo where regNo=? and Password=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(IregNo));
			pstmt.setString(2,Ipass);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				out.println("<h1><font color='green'>Login Successful </font></h1>");
				String url = "http://localhost:8080/StudentsApp/home";
				resp.sendRedirect(url);
			}
			else
			{
				out.println("<h1><font color='red'>Invalid Crediantial.... Please try Again...</font></h1>");
			}
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
			try
			{
				if(con!=null)
				{
					con.close();
				}
				if(pstmt!=null)
				{
					pstmt.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

// Hellso
