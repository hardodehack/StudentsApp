package hardik.studentsApp.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProfile extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String ISregNo = req.getParameter("rN");
		
		String Ifname = req.getParameter("fn");
		String Imname = req.getParameter("mn");
		String Ilname = req.getParameter("ln");
		String Igfname = req.getParameter("gfn");
		String Igmname = req.getParameter("gmn");
		String Iglname = req.getParameter("gln");
		String IisAdmin = req.getParameter("isAdmin");
		String Ipassword = req.getParameter("pass");
		
		
		
		Connection con = null;
		CallableStatement cstmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String url = "jdbc:mysql://localhost:3306/becm45_db?user=j2ee&password=j2ee";
			con=DriverManager.getConnection(url);
			
			String query = "call CrePro(?,?,?,?,?,?,?,?,?)";
			cstmt = con.prepareCall(query);
			cstmt.setInt(1,Integer.parseInt(ISregNo));
			cstmt.setString(2,Ifname);
			cstmt.setString(3,Imname);
			cstmt.setString(4,Ilname);
			cstmt.setString(5,Igfname);
			cstmt.setString(6,Igmname);
			cstmt.setString(7,Iglname);
			cstmt.setString(8,IisAdmin);
			cstmt.setString(9,Ipassword);
			
			
			
			int bVal = cstmt.executeUpdate();
		
			if(bVal>0)
			{
				out.println("<h1><font color='green'>Profile Created</font></h1>");
			}
			else
			{
				out.println("<h1><font color='red'>Something wrong happen... try again</font></h1>");
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("<h1><font color='red'>Already Registered... Try Different regNo</font></h1>");
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
				if(cstmt!=null)
				{
					cstmt.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
