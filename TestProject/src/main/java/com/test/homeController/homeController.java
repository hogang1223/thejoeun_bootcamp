package com.test.homeController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.command.Command;
import com.test.command.ContentCommand;
import com.test.command.DeleteCommand;
import com.test.command.ListCommand;
import com.test.command.ModifyCommand;
import com.test.command.WriteCommand;

/**
 * Servlet implementation class homeController
 */
@WebServlet("*.do")
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public homeController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<<<<< DoGet() >>>>>");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<<<<< DoGet() >>>>>");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<<< actionDo() >>>");
		
		String uri = null;
		String conPath = null;
		String com = null;
		String viewPage = null;
		Command command = null;

		request.setCharacterEncoding("utf-8");

		uri = request.getRequestURI();
		conPath = request.getContextPath();
		com = uri.substring(conPath.length());
		System.out.println("<Request com : " + com + ">");

		switch (com) {
		case "/list.do":
			command = new ListCommand();
			command.excute(request, response);
			viewPage = "list.jsp";
			break;

		case "/content.do":
			command = new ContentCommand();
			command.excute(request, response);
			viewPage = "content.jsp";
			break;
		case "/write_enter.do":
			viewPage = "write.jsp";
			break;
		case "/write.do":
			command = new WriteCommand();
			command.excute(request, response);
			viewPage = "list.do?page=1";
			break;
		case "/modify.do":
			command = new ModifyCommand();
			command.excute(request, response);
			viewPage = "list.do?page=1";
			break;
		case "/delete.do":
			command = new DeleteCommand();
			command.excute(request, response);
			viewPage = "list.do?page=1";
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
