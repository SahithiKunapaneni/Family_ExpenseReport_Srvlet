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
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/showEditExpense")

public class ShowEditExpenseServlet extends HttpServlet {

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
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));
		
		session.setAttribute("expenseId",expenseId);
		
		// 2 call the service by passing the input to execute the bussiness logic
		Expense expense = ferService.getExpense(expenseId);

		// 3 Show the status
		if(expense ==null) {
			out.println("No expense found to edit ....!");
		}
		else {
		out.println("<table align='center'border='2'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("EditExpense");
		out.println(" </td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Expensetype</td>");
		out.println("  <td>");
		out.println("<input type='text' name='type' value='"+expense.getType()+"'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Date</td>");
		out.println("  <td>");
		out.println("<input type='text' name='date' value='"+expense.getDate()+"'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Price</td>");
		out.println("  <td>");
		out.println("<input type='text' name='price' value='"+expense.getPrice()+"'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Number Of Items</td>");
		out.println("  <td>");
		out.println("<input type='text' name='numberOfItems' value='"+expense.getNumberOfItems()+"'>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>Total</td>");
		out.println("  <td>");
		out.println("<input type='text' name='total' value='"+expense.getTotal()+"'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>By Whom</td>");
		out.println("  <td>");
		out.println("<input type='text' name='byWhom' value='"+expense.getByWhom()+"'>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan='2' align='center' >");
		out.println("<input type='submit' value ='Edit Expense' onclick=\"javascript: submitForm('editExpense')\">");
		out.println(" </td>");
		out.println("</tr>");
		out.println("</table>");
		}
		// footer
		layoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
