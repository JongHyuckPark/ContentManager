package com.company.biz.content;

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

@WebServlet("/DeleteContentCtrl")
public class DeleteContentCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("/DeleteContentCtrl");

		int contentcode = Integer.parseInt(request.getParameter("contentcode"));
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from content_tbl where contentcode=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, contentcode);

			int cnt = stmt.executeUpdate();
			
			System.out.println(cnt + "개 레코드 삭제");

			out.print("<script>alert('글 삭제 완료');location.href='GetContentListCtrl';</script>");

			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}