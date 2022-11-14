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
import javax.servlet.http.HttpSession;

import com.company.biz.common.JDBCUtil;
import com.company.biz.vo.ContentVO;

@WebServlet("/GetContentCtrl")
public class GetContentCtrl extends HttpServlet{

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("/GetContentCtrl");

			// 1. 접속한 유저 이름 추출
			// 로그인을 안했으면 로그인 페이지로 이동시킨다.
			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("name");
			String id = (String) session.getAttribute("id");
			
			if (name == null)
				response.sendRedirect("login.jsp");
			
			int contentcode=Integer.parseInt(request.getParameter("contentcode"));
			
			Connection conn=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			
			try {
				conn=JDBCUtil.getConnection();
				
				// 조회수 1카운트 처리를 하는 구문
				
				String sql="update content_tbl set cnt=cnt+1 where contentcode=?";
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, contentcode);
				stmt.executeUpdate();
				stmt.close();
				
				// 여기부터는 조회
				
				sql="select contentcode,genre,contentname,author,publisher,publicationdate,reservation,price,cnt from content_tbl where contentcode=?";
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, contentcode);
				rs=stmt.executeQuery();
				
				
				ContentVO vo = null;
				if(rs.next()) {
					vo=new ContentVO();
					vo.setContentcode(rs.getInt("contentcode"));
					vo.setGenre(rs.getString("genre"));
					vo.setContentname(rs.getString("contentname"));
					vo.setAuthor(rs.getString("author"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setPublicationdate(rs.getString("publicationdate"));
					vo.setReservation(rs.getString("reservation"));
					vo.setPrice(rs.getInt("price"));
					vo.setCnt(rs.getInt("cnt"));
				}
				
				request.setAttribute("vo", vo);
				
				stmt.close();
				rs.close();
				
//				sql="select boardseq,seq,nickname,to_char(regdate,'yyyy-mm-dd HH24:MI:SS') as regdate,content,userid from replyboard where boardseq=? order by seq desc";
//				
//				stmt=conn.prepareStatement(sql);
//				stmt.setInt(1, seq);
//				rs=stmt.executeQuery();
//				
//				ArrayList<ReplyBoardVO> replyList=new ArrayList<ReplyBoardVO>();
//				
//				while(rs.next()) {
//					ReplyBoardVO replyVo=new ReplyBoardVO();
//					replyVo.setBoardseq(rs.getInt("boardseq"));
//					replyVo.setSeq(rs.getInt("seq"));
//					replyVo.setNickname(rs.getString("nickname"));
//					replyVo.setContent(rs.getString("content"));
//					replyVo.setRegdate(rs.getString("regdate"));
//					replyVo.setUserid(rs.getString("userid"));
//					
//					replyList.add(replyVo);
//				}
//				
//				request.setAttribute("replyList", replyList);
//				
//				// 댓글의 댓글을 검색하여 getBoard.jsp에 값을 전달해야 하므로 자원을 닫는다.
//				
//				stmt.close();
//				rs.close();
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("getContent.jsp");
				dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			
		}

}
