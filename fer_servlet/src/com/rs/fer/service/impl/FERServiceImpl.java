package com.rs.fer.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.DBUtil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {
		boolean isRegister = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "INSERT INTO USER (FIRSTNAME,MIDDLENAME,LASTNAME,EMAIL,USERNAME,PASSWORD,MOBILE) VALUES (?,?,?,?,?,?,?)";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getMobile());

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isRegister = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// 5
			DBUtil.closeConnection(connection);
		}
		return isRegister;
	}

	@Override
	public int login(String username, String password) {
		int userId =0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "SELECT * FROM USER WHERE USERNAME =? AND PASSWORD =?";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			// 4.Execute the statement object

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				userId =resultset.getInt(1);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// 5
			DBUtil.closeConnection(connection);
		}
		return userId;
	}

	@Override
	public boolean addExpense(Expense expense) {
		boolean isAddExpense = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "INSERT INTO EXPENSE (TYPE,DATE,PRICE,NUMBEROFITEMS,TOTAL,BYWHOM,USERID) VALUES (?,?,?,?,?,?,?)";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getUserId());

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();

			if (numberOfRecAffected > 0) {
				isAddExpense = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5
			DBUtil.closeConnection(connection);
		}
		return isAddExpense;
	}

	@Override
	public boolean editExpense(Expense expense) {
		boolean isEditExpense = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "UPDATE EXPENSE SET TYPE=?,DATE=?,PRICE=?,NUMBEROFITEMS =?,TOTAL=?,BYWHOM=? WHERE ID =? ";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getId());

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();

			if (numberOfRecAffected > 0) {
				isEditExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isEditExpense;
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		boolean isDeleteExpense = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "DELETE FROM EXPENSE WHERE ID =?";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, expenseId);

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isDeleteExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isDeleteExpense;
	}

	@Override
	public boolean resetPassword(int userId, String newPassword, String currentPassword) {
		boolean isResetPassword = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "UPDATE USER SET PASSWORD =? WHERE ID =? AND PASSWORD =?";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, currentPassword);

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();

			if (numberOfRecAffected > 0) {
				isResetPassword = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5
			DBUtil.closeConnection(connection);
		}
		return isResetPassword;
	}

	@Override
	public List<Expense> getExpenseOptions(int userId) {
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID =? ";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);

			// 4.Execute the statement object

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				// a get the column data

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				Float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				int UserId = resultset.getInt(8);

				// b. load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(UserId);

				// c Add the expense object to list collection object

				expenses.add(expense);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public Expense getExpense(int expenseId) {

		Expense expense = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "SELECT * FROM EXPENSE WHERE ID =? ";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, expenseId);

			// 4.Execute the statement object

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				// a get the column data

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				Float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				int UserId = resultset.getInt(8);

				// b. load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(UserId);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expense;
	}

	@Override
	public List<Expense> getExpenseReport(int userId, String expenseType, String fromDate, String toDate) {
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID =?  AND TYPE =? AND DATE BETWEEN ? AND ?";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);

			preparedStatement.setString(2, expenseType);
			preparedStatement.setString(3, fromDate);

			preparedStatement.setString(4, toDate);

			// 4.Execute the statement object

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				// a get the column data

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				Float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				int UserId = resultset.getInt(8);

				// b. load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(UserId);

				// c Add the expense object to list collection object

				expenses.add(expense);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public User getUser(int UserId) {
		User user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "SELECT U.*,A.* FROM USER U LEFT JOIN ADDRESS A ON U.ID=A.USERID WHERE U.ID=?  ";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, UserId);

			// 4.Execute the statement object

			ResultSet resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				// a get the column data

				// user column data
				int id = resultset.getInt(1);
				String firstname = resultset.getString(2);
				String middlename = resultset.getString(3);
				String lastname = resultset.getString(4);
				String email = resultset.getString(5);
				String Username = resultset.getString(6);
				String Password = resultset.getString(7);
				String mobile = resultset.getString(8);

				// address column data
				int addrID = resultset.getInt(9);
				String lineone = resultset.getString(10);
				String linetwo = resultset.getString(11);
				String city = resultset.getString(12);
				String state = resultset.getString(13);
				String pincode = resultset.getString(14);
				String country = resultset.getString(15);
				int uid = resultset.getInt(16);

				// c. load user column data into the user object
				user = new User();
				user.setId(id);
				user.setFirstName(firstname);
				user.setMiddleName(middlename);
				user.setLastName(lastname);
				user.setEmail(email);
				user.setUsername(Username);
				user.setPassword(Password);
				user.setMobile(mobile);
				// d. load the address column data into the address object
				Address address = new Address();
				address.setId(addrID);
				address.setLineone(lineone);
				address.setLinetwo(linetwo);
				address.setCity(city);
				address.setState(state);
				address.setPinCode(pincode);
				address.setCountry(country);
				address.setUserId(uid);
				// e load the address object into the user
				user.setAddress(address);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// 5
			DBUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean isUserUpdate = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1 & 2

			connection = DBUtil.getConnection();
			// 3. create the statement object
			String query = "UPDATE USER SET FIRSTNAME =?,MIDDLENAME=?,LASTNAME =?,EMAIL=?,MOBILE =?  WHERE ID =?";

			// 3.1 set the parameter values into the prepared Statement

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getMobile());
			preparedStatement.setInt(6, user.getId());

			// 4.Execute the statement object

			int numberOfRecAffected = preparedStatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				Address address = user.getAddress();
				int addressId = address.getId();
				if (addressId == 0) {
					// insert
					query = "INSERT INTO ADDRESS (LINE1,LINE2,CITY,STATE,PINCODE,COUNTRY,USERID) VALUES (?,?,?,?,?,?,?)";

					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, address.getLineone());
					preparedStatement.setString(2, address.getLinetwo());
					preparedStatement.setString(3, address.getCity());
					preparedStatement.setString(4, address.getState());
					preparedStatement.setString(5, address.getPinCode());
					preparedStatement.setString(6, address.getCountry());
					preparedStatement.setInt(7, user.getId());

					numberOfRecAffected = preparedStatement.executeUpdate();

				} else {
					query = "UPDATE ADDRESS SET LINE1=?,LINE2 =?,CITY=?,STATE =?,PINCODE =?,COUNTRY =? WHERE ID =?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, address.getLineone());
					preparedStatement.setString(2, address.getLinetwo());
					preparedStatement.setString(3, address.getCity());
					preparedStatement.setString(4, address.getState());
					preparedStatement.setString(5, address.getPinCode());
					preparedStatement.setString(6, address.getCountry());
					preparedStatement.setInt(7, addressId);

					numberOfRecAffected = preparedStatement.executeUpdate();
				}

				if (numberOfRecAffected > 0) {
					isUserUpdate = true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			// 5
			DBUtil.closeConnection(connection);
		}
		return isUserUpdate;
	}

}
