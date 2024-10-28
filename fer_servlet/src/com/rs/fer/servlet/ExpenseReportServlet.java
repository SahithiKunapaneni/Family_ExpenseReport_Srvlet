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

@WebServlet("/expenseReport")

public class ExpenseReportServlet extends HttpServlet {

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
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		String expenseType = request.getParameter("type");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		// 2 call the service by passing the input to execute the bussiness logic

		List<Expense> expenses = ferService.getExpenseReport(userId, expenseType, fromDate, toDate);

		if (expenses.isEmpty()) {
			out.println("No Expense found ....");
		} else {
			out.println("<table align='center'border='2' width='600px'>");
			out.println("<tr>");
			out.println("	<td colspan='10' align='center'  width='600px'>");
			out.println("	    Expense Report");
			out.println("	</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("	<td  width='100px'>Expensetype</td>");
			out.println("		<td width='100px'>Date</td>");

			out.println("		<td width='100px'>Price</td>");
			out.println("		<td width='100px'>NoOfItems</td>");
			out.println("		<td width='100px'>Total</td>");
			out.println("		<td width='100px'>ByWhom</td>");
			out.println("</tr>");
			for (Expense expense : expenses) {
				out.println("<tr>");
				out.println("  <td width='100px'>");
				out.println(expense.getType());
				out.println("</td>");
				out.println("  <td width='100px'>");
				out.println(expense.getDate());
				out.println("</td>");
				out.println("  <td width='100px'>");
				out.println(expense.getPrice());
				out.println("</td>");
				out.println("  <td width='100px'>");
				out.println(expense.getNumberOfItems());
				out.println("</td>");
				out.println("  <td width='100px'>");
				out.println(expense.getTotal());
				out.println("</td>");
				out.println("  <td  width='100px'>");
				out.println(expense.getByWhom());
				out.println("</td>");

			}
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
