package com.jsplec.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MulTable_01S
 */
@WebServlet("/MulTable_01S")
public class MulTable_01S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MulTable_01S() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int dan = Integer.parseInt(request.getParameter("dan"));
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>" + dan+ "단 - 결과화면</title></head>");
		out.println("<body>");

		
		for(int i=1; i<10; i++) {
			out.println(dan + " x " + i +" = " + (dan*i)+"<br>");
		}
		out.println("</body>");
		out.println("</html>");
	
	}

}
