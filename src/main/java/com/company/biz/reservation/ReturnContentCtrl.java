package com.company.biz.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.biz.common.JDBCUtil;


@WebServlet("/ReturnContentCtrl")
public class ReturnContentCtrl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ReturnContentCtrl");
		
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("id");
		int contentcode = Integer.parseInt(request.getParameter("contentcode"));
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from inout where id=? and contentcode=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setInt(2, contentcode);

			int cnt = stmt.executeUpdate();

			System.out.println(cnt + "개 레코드 삭제");
			
			stmt.close();
			
			sql="update content_tbl set reservation=? where contentcode=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, "Y");
			stmt.setInt(2, contentcode);
			
			int cnt2= stmt.executeUpdate();
			
			if(cnt2>0)
				response.sendRedirect("UserInfo");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
