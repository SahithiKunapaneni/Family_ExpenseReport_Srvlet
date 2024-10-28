package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/showContactInfo")

public class ShowContactInfoServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		ferService = new FERServiceImpl();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header & leftFrame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
		// Body
		// 1.get the input
             User user = (User)session.getAttribute("user");		
		// 2 .Update name field values into user object
         user.setFirstName(request.getParameter("firstName"));
         
         user.setMiddleName(request.getParameter("middleName"));
         user.setLastName(request.getParameter("lastName"));
		// 3 Show the status
		out.println("<table align='center'border='2'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("Contact Info");
		out.println(" </td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Email</td>");
		out.println("  <td>");
		out.println("<input type='text' name='email' value='"+user.getEmail()+"'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Mobile</td>");
		out.println("  <td>");
		out.println("<input type='text' name='mobile' value='"+user.getMobile()+"'>");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("<input type='submit' value ='Next' onclick=\"javascript: submitForm('showAddressInfo')\">");
		out.println(" </td>");
		out.println("</tr>");
		out.println("</table>");
		// footer
		layoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
