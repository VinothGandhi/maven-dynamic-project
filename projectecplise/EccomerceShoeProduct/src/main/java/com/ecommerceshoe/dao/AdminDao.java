package com.ecommerceshoe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ecommerceshoe.interfaceDao.AdminDaoInterface;
import com.ecommerceshoe.model.Admin;
import com.ecommerceshoe.model.Users;

public class AdminDao implements AdminDaoInterface{
	public  Admin validateAdmin(String admin_email, String password) {

		String AdminQuery = "select * from admins where admin_email ='" + admin_email + "'and password='" + password + "'";
		Connection con = ConnectionUtil.getDbconnection();
		Admin admin = null;
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(AdminQuery);
			if (rs.next()) {
				// System.out.println(rs.getString(2)+" "+rs.getLong(3));

				admin = new Admin(rs.getString(1),rs.getString(2) );
			}

		} catch (SQLException e) {
			// T5ODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Statement error");
		}

		return admin;

	}

	public Admin AdminQuery(String admin_email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
