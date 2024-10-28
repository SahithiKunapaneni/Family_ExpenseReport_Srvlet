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

@WebServlet("/showEditexpenseOptions")

public class ShowEditExpenseOptionsServlet extends HttpServlet {
	
	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		 ferService = new FERServiceImpl();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// Header & leftFrame
				PrintWriter out = response.getWriter();
				HttpSession session =request.getSession();
				layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
				// Body
				// 1.get the input
                 int userId =(int) session.getAttribute("userId");
				// 2 call the service by passing the input to execute the bussiness logic
                 List<Expense> expenses = ferService.getExpenseOptions(userId);
                 

				// 3 Show the status
				if(expenses.isEmpty()) {
					out.println("No expense found to edit ....!");
				}
				else
				{
					out.println("Expense ID:");
					out.println("&nbsp;");
					out.println("<select name ='expenseId'>");
					out.println(" <option  value=''>Please select Expense ID</option>");
					
					int value =0;
					String text =null;
					for(Expense expense : expenses) {
						value =expense.getId();
						text = value+ "--"+expense.getType()+"--"+expense.getDate()+"--"+expense.getTotal()+"--"+expense.getByWhom();
						
					out.println(" <option value ='"+value +"'>"+text +"</option>");
					}
					out.println("</select>");
					out.println(" &nbsp;&nbsp;&nbsp;");
					out.println("<input type='submit' value ='Next' onclick=\"javascript: submitForm('showEditExpense')\">");
					
				}
				// footer 
				layoutUtil.showFooter(request, response);
			
	}
	@Override
	public void destroy() {
		 ferService = null;
	}
}
