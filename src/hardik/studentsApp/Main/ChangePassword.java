package hardik.studentsApp.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String ISregNo = req.getParameter("regNoI");
		String Iopass = req.getParameter("oldI");
		String Inpass = req.getParameter("newI");
		String IRnpass = req.getParameter("rnewI");
		if(Inpass==IRnpass)
		{
			out.println("<h1><font color='red'>New Password not matching... try again</font></h1>");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try 
		{
			out.println(ISregNo);
			out.println(Iopass);
			out.println(Inpass);
			out.println(IRnpass);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String url = "jdbc:mysql://localhost:3306/becm45_db?user=j2ee&password=j2ee";
			con=DriverManager.getConnection(url);
			
			String query = "update soinfo set Password=? where regNo=? and Password=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(2,Integer.parseInt(ISregNo));
			pstmt.setString(3,Iopass);
			pstmt.setString(1,Inpass);
			
			
			
			int bVal = pstmt.executeUpdate();
			
			if(bVal>0)
			{
				out.println("<h1><font color='green'>Password Successfully Changed</font></h1>");
			}
			else
			{
				out.println("<h1><font color='red'>Old Password not matching... try again</font></h1>");
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
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	

}
