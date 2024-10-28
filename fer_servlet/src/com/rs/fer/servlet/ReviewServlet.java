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

@WebServlet("/review")

public class ReviewServlet extends HttpServlet {

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
		// 2 .Update Address field values into user object

		Address address = user.getAddress();
		address.setLineone(request.getParameter("lineOne"));
		address.setLinetwo(request.getParameter("lineTwo"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setPinCode(request.getParameter("pinCode"));
		address.setCountry(request.getParameter("country"));

		// 3 Show the status
		out.println("<table align='center'border='2'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("Review");
		out.println(" </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>First Name</td>");
		out.println("  <td>");
		out.println("<input type='text' name='firstName' value='" + user.getFirstName() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Middle Name</td>");
		out.println("  <td>");
		out.println("<input type='text' name='middleName' value='" + user.getMiddleName() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name</td>");
		out.println("  <td>");
		out.println("<input type='text' name='lastName' value='" + user.getLastName() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Email</td>");
		out.println("  <td>");
		out.println("<input type='text' name='email' value='" + user.getEmail() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Mobile</td>");
		out.println("  <td>");
		out.println("<input type='text' name='mobile' value='" + user.getMobile() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Line1</td>");
		out.println("  <td>");
		out.println("<input type='text' name='lineOne' value='" + address.getLineone() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Line2</td>");
		out.println("  <td>");
		out.println("<input type='text' name='lineTwo' value='" + address.getLinetwo() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>City</td>");
		out.println("  <td>");
		out.println("<input type='text' name='city' value='" + address.getCity() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>State</td>");
		out.println("  <td>");
		out.println("<input type='text' name='state' value='" + address.getState() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>PinCode</td>");
		out.println("  <td>");
		out.println("<input type='text' name='pinCode' value='" + address.getPinCode() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>County</td>");
		out.println("  <td>");
		out.println("<input type='text' name='country' value='" + address.getCountry() + "'disabled ='true'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println(
				"<input type='submit' value ='Update Profile' onclick=\"javascript: submitForm('updateProfile')\">");
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
