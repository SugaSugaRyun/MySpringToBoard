
package com.example.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
//import com.example.board.JDBCUtil;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setTemplate (JdbcTemplate template){
		this.jdbcTemplate = template;
	}


	private final String BOARD_INSERT = "insert into BOARD (category, title, writer, content) values (?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set category=?, title=?, writer=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD  where seq=?";
	private final String BOARD_GET = "select * from BOARD  where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";

	public int insertBoard(BoardVO vo) {
		return jdbcTemplate.update(BOARD_INSERT,
				new Object[]{vo.getCategory(), vo.getTitle(), vo.getWriter(), vo.getContent()});
	}

	// 글 삭제
	public int deleteBoard(int seq) {
		return jdbcTemplate.update(BOARD_DELETE,
				new Object[]{seq});
	}
	public int updateBoard(BoardVO vo) {
		return jdbcTemplate.update(BOARD_UPDATE,
				new Object[]{vo.getCategory(), vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getSeq()});
	}	
	
	public BoardVO getBoard(int seq) {
		return jdbcTemplate.queryForObject(BOARD_GET,
				new Object[]{seq},
				new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}

	public List<BoardVO> getBoardList() {
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

	class BoardRowMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt("seq"));
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setContent(rs.getString("content"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setCategory(rs.getString("category"));
			return vo;
		}
	}
}
