

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.Main;




/**
 * Servlet implementation class Servlet2
 */

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	ArrayList<String> Name = new ArrayList<>();
	ArrayList<String> Comments = new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

//data going from here
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
System.out.println("get");
System.out.println(Name.toString());
	    HttpSession session = request.getSession();
String key1="go";
String key2="yahoo";
key1=(String) Name.get(0);
key2=(String) Comments.get(0);
	    // Check if the received values are not null or empty before setting them in the session
	    if (key1 != null && !key1.isEmpty()) {
	        session.setAttribute("key1", Name);
	    }
	    if (key2 != null && !key2.isEmpty()) {
	        session.setAttribute("key2", Comments);
	    }

	    RequestDispatcher ds = request.getRequestDispatcher("/Feedback.jsp");
	    ds.forward(request, response);
	}

	//data coming from here
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String key1 = request.getParameter("key1");
		    String key2 = request.getParameter("key2");

		    System.out.println("Received key1: " + key1);
		    System.out.println("Received key2: " + key2);
		    System.out.println("post");

			   Name.add(key1);
			   Comments.add(key2);
	
      
		
		

    }
}

