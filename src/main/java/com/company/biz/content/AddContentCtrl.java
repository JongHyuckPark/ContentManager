package com.company.biz.content;

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


@WebServlet("/AddContentCtrl")
public class AddContentCtrl extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/AddContentCtrl");
		// 1. 접속한 유저 이름 추출
		// 로그인을 안했으면 로그인 페이지로 이동시킨다.
		HttpSession session=request.getSession();
		String name=(String) session.getAttribute("name");
		if(name==null) response.sendRedirect("login.jsp");
		
		// insert,update,delete,또는 select의 조건값이 넘어올 경우 넘어오는 값을 받는다.
		request.setCharacterEncoding("UTF-8");
		String content=request.getParameter("content");
		String contentname=request.getParameter("contentname");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
		String publicationdate=request.getParameter("publicationdate");
		int price=Integer.parseInt(request.getParameter("price"));
		
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try {
			conn=JDBCUtil.getConnection();
			String sql="insert into content_tbl(contentcode,content,contentname,author,publisher,publicationdate,price)\r\n"
					+ "values((select nvl(max(contentcode)+1,000001) from content_tbl),?,?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,content);
			stmt.setString(2,contentname);
			stmt.setString(3,author);
			stmt.setString(4,publisher);
			stmt.setString(5,publicationdate);
			stmt.setInt(6,price);
			
			int cnt1=stmt.executeUpdate();
			
			if(cnt1>0)
				response.sendRedirect("GetContentListCtrl");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	
	}
}
