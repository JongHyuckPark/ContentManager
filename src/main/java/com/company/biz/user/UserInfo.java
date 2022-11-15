package com.company.biz.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.biz.common.JDBCUtil;
import com.company.biz.vo.InoutVO;
import com.company.biz.vo.UserVO;

@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/UserInfo");
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String id = (String) session.getAttribute("id");
		
		if (name == null)
			response.sendRedirect("login.jsp");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			conn=JDBCUtil.getConnection();
			
			// 조회수 1카운트 처리를 하는 구문
			
			String sql="select * from users where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			
			UserVO uvo=null;
			
			if(rs.next()) {
				uvo=new UserVO();
				uvo.setId(rs.getString("id"));
				uvo.setPassword(rs.getString("password"));
				uvo.setName(rs.getString("name"));
				uvo.setPhone(rs.getString("phone"));
			}
			
			request.setAttribute("uvo", uvo);
			
			stmt.close();
			rs.close();
			
			// 여기부터는 조회
			
			sql="select id,name,contentcode,genre,contentname,to_char(rentaldate,'yyyy-mm-dd') as rentaldate,to_char(sysdate+7,'yyyy-mm-dd') as returndate,price from inout where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			
			ArrayList<InoutVO> usersList=new ArrayList<InoutVO>();
			InoutVO ivo = null;
			while(rs.next()) {
				System.out.println("진입");
				ivo=new InoutVO();
				ivo.setId(rs.getString("id"));
				ivo.setName(rs.getString("name"));
				ivo.setContentcode(rs.getInt("contentcode"));
				ivo.setGenre(rs.getString("genre"));
				ivo.setContentname(rs.getString("contentname"));
//				ivo.setRentaldate(rs.getDate("rentaldate"));
//				ivo.setReturndate(rs.getDate("rentaldate"));
				ivo.setRentaldate(rs.getString("rentaldate"));
				ivo.setReturndate(rs.getString("returndate"));
				ivo.setPrice(rs.getInt("price"));
				
				usersList.add(ivo);
			}
			
			request.setAttribute("usersList", usersList);

			RequestDispatcher dispatcher=request.getRequestDispatcher("userInfo.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
	}

}
