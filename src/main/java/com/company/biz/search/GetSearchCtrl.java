package com.company.biz.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.biz.common.JDBCUtil;
import com.company.biz.vo.ContentVO;

@WebServlet("/GetSearchCtrl")
public class GetSearchCtrl extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("/GetSearchCtrl");

		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = null;
			if (searchCondition.equals("contentname")) {
//				--책이름 검색
				sql = "select * from CONTENT_TBL where " + searchCondition + " like '%' || ? || '%'";
			} else if (searchCondition.equals("genre")) {
//				장르 검색
				sql = "select * from CONTENT_TBL where " + searchCondition + " like '%' || ? || '%'";
			} else if (searchCondition.equals("author")) {
//				저자 검색
				sql = "select * from CONTENT_TBL where " + searchCondition + " like '%' || ? || '%'";
			} else {
				response.sendRedirect("getContentList.jsp");
			}

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, searchKeyword);
			rs = stmt.executeQuery();
			
			System.out.println(searchCondition+" "+searchKeyword);
			System.out.println("검색 쿼리 문제 없이 수행 완료");

			ArrayList<ContentVO> contentList = new ArrayList<>();
			while (rs.next()) {
				ContentVO vo = new ContentVO();
				vo.setContentcode(rs.getInt("contentcode"));
				vo.setGenre(rs.getString("genre"));
				vo.setContentname(rs.getString("contentname"));
				vo.setAuthor(rs.getString("author"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPublicationdate(rs.getString("publicationdate"));
				vo.setReservation(rs.getString("reservation"));
				vo.setPrice(rs.getInt("price"));
				vo.setCnt(rs.getInt("cnt"));

//				vo에 있지만 content_tbl에는 없는 녀석들...
//				vo.setRentaldate(rs.getString("rentaldate"));
//				vo.setReturndate(rs.getString("returndate"));

				contentList.add(vo);
			}
			
			request.setAttribute("contentList", contentList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("getSearchContentList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

	}

}
