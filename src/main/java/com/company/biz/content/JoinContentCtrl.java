package com.company.biz.content;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.biz.common.JDBCUtil;

@WebServlet("/JoinContentCtrl")
public class JoinContentCtrl extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/JoinContentCtrl");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			conn=JDBCUtil.getConnection();
			
			String sql="select nvl(max(contentcode),0)+1 as contentcode from content_tbl";
			stmt=conn.prepareStatement(sql);
			
			rs=stmt.executeQuery();
			Integer contentcode =null;
			if(rs.next()) {
				contentcode=rs.getInt("contentcode");
			}
			System.out.println("contentcode : " + contentcode);
			
			req.setAttribute("contentcode", contentcode);
			
			RequestDispatcher dispatcher=req.getRequestDispatcher("insertContent.jsp");
			dispatcher.forward(req, resp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
	}
}
