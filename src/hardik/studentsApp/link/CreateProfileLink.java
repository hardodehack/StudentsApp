package hardik.studentsApp.link;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateProfileLink extends HttpServlet 
{
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session == null)
		{
			out.println("Invalid Session");
		}
		else
		{
			resp.setContentType("text/html");
		
			RequestDispatcher dispatcherh = req.getRequestDispatcher("/header.html");
			dispatcherh.include(req, resp);
		
			RequestDispatcher dispatchers = req.getRequestDispatcher("/createProfile.html");
			dispatchers.include(req, resp);
			
			RequestDispatcher dispatcherf = req.getRequestDispatcher("/footer.html");
			dispatcherf.include(req, resp);
		}
	}
}
