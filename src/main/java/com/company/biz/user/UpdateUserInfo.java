package com.company.biz.user;

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
import javax.servlet.http.HttpSession;

import com.company.biz.common.JDBCUtil;


@WebServlet("/UpdateUserInfo")
public class UpdateUserInfo extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/UpdateUserInfo");
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=response.getWriter();

		// 1. 접속한 유저 이름 추출
		// 로그인을 안했으면 로그인 페이지로 이동시킨다.
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		if (name == null)
			response.sendRedirect("login.jsp");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=JDBCUtil.getConnection();
			String sql="update users set password=?,name=?,phone=? where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, name);
			stmt.setString(3, phone);
			stmt.setString(4, id);
			
			int cnt=stmt.executeUpdate();
			
			System.out.println(cnt+"개 레코드 수정");
			
			out.print("<script>alert('정보 수정 완료');location.href='GetContentListCtrl';</script>");
			
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
