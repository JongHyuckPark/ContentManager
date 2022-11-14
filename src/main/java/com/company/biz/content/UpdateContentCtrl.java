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
import javax.servlet.http.HttpSession;

import com.company.biz.common.JDBCUtil;


@WebServlet("/UpdateContentCtrl")
public class UpdateContentCtrl extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/UpdateContentCtrl");
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=response.getWriter();

		// 1. 접속한 유저 이름 추출
		// 로그인을 안했으면 로그인 페이지로 이동시킨다.
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		if (name == null)
			response.sendRedirect("login.jsp");
		
		int contentcode=Integer.parseInt(request.getParameter("contentcode"));
		String genre=request.getParameter("genre");
		String contentname=request.getParameter("contentname");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
		String publicationdate=request.getParameter("publicationdate");
		String reservation=request.getParameter("reservation");
		int price=Integer.parseInt(request.getParameter("price"));
		
		System.out.println(contentcode+" "+genre+" "+contentname+" "+author+" "+publisher+" "+publicationdate+" "+price);
		
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=JDBCUtil.getConnection();
			String sql="update content_tbl set genre=?,contentname=?,author=?,publisher=?,publicationdate=?,reservation=?,price=? where contentcode=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, genre);
			stmt.setString(2, contentname);
			stmt.setString(3, author);
			stmt.setString(4, publisher);
			stmt.setString(5, publicationdate);
			stmt.setString(6, reservation);
			stmt.setInt(7, price);
			stmt.setInt(8, contentcode);
			
			int cnt=stmt.executeUpdate();
			
			System.out.println(cnt+"개 레코드 수정");
			
			out.print("<script>alert('글 수정 완료');location.href='GetContentListCtrl';</script>");
			
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
