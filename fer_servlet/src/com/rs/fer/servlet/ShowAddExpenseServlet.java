package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.util.layoutUtil;

@WebServlet("/showAddExpense")
public class ShowAddExpenseServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Header & leftFrame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
		// Body
		request.getRequestDispatcher("AddExpense.html").include(request, response);
		// footer
		layoutUtil.showFooter(request, response);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        
        
	}
}
