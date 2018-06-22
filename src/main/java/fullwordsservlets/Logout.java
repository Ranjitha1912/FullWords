package fullwordsservlets;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet(
    name = "Logout",
    urlPatterns = {"/logout"}
)

public class Logout extends HttpServlet
{
	
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException , ServletException
{
	
PrintWriter pr=res.getWriter();
res.setContentType("text/html");
HttpSession session=req.getSession(); 

session.invalidate();

	
	
	res.sendRedirect("index.html");
	
	

}
}
	

