package hardik.studentsApp.link;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchLink extends HttpServlet 
{
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession(true);
		
		resp.setContentType("text/html");
		
		RequestDispatcher dispatcherh = req.getRequestDispatcher("/header.html");
		dispatcherh.include(req, resp);
		
		RequestDispatcher dispatchers = req.getRequestDispatcher("/Search.html");
		dispatchers.include(req, resp);
		
		RequestDispatcher dispatcherf = req.getRequestDispatcher("/footer.html");
		dispatcherf.include(req, resp);
		
	}
	

}
