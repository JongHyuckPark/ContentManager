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

import com.company.biz.common.JDBCUtil;


@WebServlet("/JoinCtrl")
public class JoinCtrl extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		System.out.println("JoinCtrl");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=JDBCUtil.getConnection();
			String sql="insert into users(id,password,name,phone) values(?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, phone);
			
			int cnt=stmt.executeUpdate();
			
			System.out.println(cnt+"명 회원 추가");
			
			out.print("<script>alert('회원가입이 되었습니다.');location.href='login.jsp';</script>");
			
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
