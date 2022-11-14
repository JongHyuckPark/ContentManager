package com.company.biz.content;

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

import com.company.biz.common.JDBCUtil;
import com.company.biz.vo.ContentVO;

@WebServlet("/GetContentListCtrl")
public class GetContentListCtrl extends HttpServlet{
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("/GetContentListCtrl");
//			처음 검색서블릿 방문 시 가장 최근 페이지를 보여주기 위해 1페이지부터 시작.
//			이후에는 page값을 받아서 처리.
			
			
			Connection conn=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			
			try {
				conn=JDBCUtil.getConnection();
				
				String sql="select contentcode,content,contentname,author,publisher,publicationdate,rentaldate,returndate,price,cnt from content_tbl order by contentcode desc";

				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
				
				ArrayList<ContentVO> contentList=new ArrayList<ContentVO>();
				
				while(rs.next()) {
					ContentVO vo=new ContentVO();
					vo.setContentcode(rs.getInt("contentcode"));
					vo.setContent(rs.getString("content"));
					vo.setContentname(rs.getString("contentname"));
					vo.setAuthor(rs.getString("author"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setPublicationdate(rs.getString("publicationdate"));
					vo.setRentaldate(rs.getString("rentaldate"));
					vo.setReturndate(rs.getString("returndate"));
					vo.setPrice(rs.getInt("price"));
					vo.setCnt(rs.getInt("cnt"));
					
					contentList.add(vo);
				}
				
				request.setAttribute("contentList", contentList);
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("getContentList.jsp");
				dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}
		}
	
}
