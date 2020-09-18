package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import BEAN.ADmin;
import DB.DBconnection;

public class AccountAdminDAO {
	public static boolean findAcccount(ADmin acc) throws Exception {
		Connection conn = DBconnection.DBconnection();
		PreparedStatement ptmt = null;

		boolean rowInserted = false;
		String sql = "select * from account_admin where username = ? and pass = ? ";

		String uname = acc.getUsername();
		String pass = acc.getPass();

			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, uname);
			ptmt.setString(2, pass);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			ptmt.close();
		return false;
	}

}
