package com.jsplec.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CharacterTest_01S
 */
@WebServlet("/CharacterTest_01S")
public class CharacterTest_01S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharacterTest_01S() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] food = request.getParameterValues("food");
		
		int foodAmount = food.length;
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>성격 테스트 결과</title></head>");
		out.println("<body>");
		out.println(name+"님의 성격 테스트 결과<br>");
		out.println(color+"을 좋아하는 당신은 " + animal + " 그리고");
		
		switch(foodAmount) {
		case 1 :
			out.println(food[0]);
			break;
		case 2 :
			out.println(food[0] + "과 " + food[1]);
			break;
		case 3 : 
			out.println(food[0] + "과 " + food[1] + "과 ");
			break;
		default :
			break;
		}
		
		out.println("을 좋아하는 성격입니다.<br>");
		out.println("<br>------------------<br>");
		out.println("저장 되었습니다!");

		out.println("</body>");
		out.println("</html>");
	
	}

}
