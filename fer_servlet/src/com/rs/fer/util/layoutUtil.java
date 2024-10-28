package com.rs.fer.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class layoutUtil {

	public static void showHeaderAndLeftFrame(HttpServletRequest request, HttpServletResponse response, PrintWriter out,
			HttpSession session) throws ServletException, IOException {
		// Header

		request.getRequestDispatcher("Layout/Header.html").include(request, response);
		out.println(session.getAttribute("username"));
		// Left frame
		request.getRequestDispatcher("Layout/LeftFrame.html").include(request, response);

	}

	public static void showFooter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// footer 
		request.getRequestDispatcher("Layout/Footer.html").include(request, response);
	}

}
