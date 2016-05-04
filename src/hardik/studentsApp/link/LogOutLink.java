package hardik.studentsApp.link;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutLink extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);
		RequestDispatcher dispatcher;
		
			session.invalidate();
			out.println("<html><h1><font color = '\"green'\">Successfully Loged Out....</font></h1></html>");
			dispatcher = req.getRequestDispatcher("/home");
			dispatcher.include(req, resp);
		
		
	}

}
