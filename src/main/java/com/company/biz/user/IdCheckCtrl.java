package com.company.biz.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.biz.common.JDBCUtil;


@WebServlet("/IdCheckCtrl")
public class IdCheckCtrl extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/IdCheckCtrl");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		System.out.println(id);
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtil.getConnection();
			String sql="select * from users where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				// 해당 ID가 존재하는 경우.(중복)
				out.print("<script>alert('"+id+"는 사용할 수 없습니다.');location.href='join.jsp';</script>");
			}else {
				// ID가 존재 안함.(중복X)
				out.print("<script>alert('"+id+"는 사용할 수 있습니다.');location.href='join.jsp?id="+id+"';</script>");
			}
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}

}
