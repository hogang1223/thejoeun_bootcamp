package com.test.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
		public void excute(HttpServletRequest request, HttpServletResponse response);
		
}
