package com.company.biz.reservation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.biz.common.JDBCUtil;

@WebServlet("/GetReservationCtrl")
public class GetReservationCtrl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/GetReservationCtrl");
		
		// 1. 접속한 유저 이름 추출
		// 로그인을 안했으면 로그인 페이지로 이동시킨다.
		HttpSession session=request.getSession();
		String name=(String) session.getAttribute("name");
		String id=(String) session.getAttribute("id");
		
		if(name==null) response.sendRedirect("login.jsp");
		
		// insert,update,delete,또는 select의 조건값이 넘어올 경우 넘어오는 값을 받는다.
		request.setCharacterEncoding("UTF-8");
		int contentcode=Integer.parseInt(request.getParameter("contentcode"));

		
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try {
			conn=JDBCUtil.getConnection();
			String sql="insert into inout(id,name,contentcode,genre,contentname,rentaldate,returndate,price) \r\n"
					+ "select u.id, u.name, c.contentcode, c.genre, c.contentname,sysdate, sysdate+7, c.price \r\n"
					+ "from users u, content_tbl c \r\n"
					+ "where u.id=? and c.contentcode=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,id);
			stmt.setInt(2,contentcode);
			
			int cnt1=stmt.executeUpdate();
			

			stmt.close();
			
			sql="update content_tbl set reservation=? where contentcode=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, "N");
			stmt.setInt(2, contentcode);
			
			int cnt2= stmt.executeUpdate();
			
			if(cnt2>0)
				response.sendRedirect("GetContentListCtrl");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	
	}

}
