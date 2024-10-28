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

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/showAddressInfo")

public class ShowAddressInfoServlet extends HttpServlet {

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
		User user = (User) session.getAttribute("user");
		// 2 .Update name field values into user object
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));

		Address address = user.getAddress();
		// 3 Show the status
		out.println("<table align='center'border='2'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("Address Info");
		out.println(" </td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Line1</td>");
		out.println("  <td>");
		out.println("<input type='text' name='lineOne' value='" + address.getLineone() + "'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Line2</td>");
		out.println("  <td>");
		out.println("<input type='text' name='lineTwo' value='" + address.getLinetwo() + "'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>City</td>");
		out.println("  <td>");
		out.println("<input type='text' name='city' value='" + address.getCity() + "'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>State</td>");
		out.println("  <td>");
		out.println("<input type='text' name='state' value='" + address.getState() + "'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>PinCode</td>");
		out.println("  <td>");
		out.println("<input type='text' name='pinCode' value='" + address.getPinCode() + "'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>County</td>");
		out.println("  <td>");
		out.println("<input type='text' name='country' value='" + address.getCountry() + "'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("<input type='submit' value ='Review' onclick=\"javascript: submitForm('review')\">");
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
