package com.spring.springJDBC.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.springJDBC.vo.UserVo;

@Component
public class UserDaoImpl implements UserDao {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	private String sql = "";

	@Autowired
	public void setDataSourceTag(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<UserVo> rowMapper = (rs, idx) -> {
		UserVo vo = new UserVo();
		vo.setIdx(rs.getInt("idx"));
		vo.setMid(rs.getString("mid"));
		vo.setPwd(rs.getString("pwd"));
		vo.setName(rs.getString("name"));
		vo.setAge(rs.getInt("age"));
		vo.setGender(rs.getString("gender"));
		vo.setAddress(rs.getString("address"));
		return vo;
	};

	@Override
	public int setUserInput(UserVo vo) {
		sql = "insert into user values(default, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, vo.getMid(), vo.getPwd(), vo.getName(), vo.getAge(), vo.getGender(),
				vo.getAddress());
	}

	@Override
	public int getUserCnt() {
		sql = "select count(*) from user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	// 아이디 중복 체크 후 검색한 결과 리턴.(자료가 있으면 해당 레코드를, 없으면 Empty를 돌려보낸다.)
	@Override
	public UserVo getUserIdSearch(String mid) {
		sql = "select * from user where mid = ?";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, mid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// 전체 조회
	@Override
	public List<UserVo> getUserList() {
		sql = "select * from user order by idx desc";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int setUserDeleteOk(int idx) {
		sql = "delete from user where idx = ?";
		return jdbcTemplate.update(sql, idx);
	}

	// 회원 아이디 완전일치 검색 - 1건만 검색
	@Override
	public UserVo getUserIdxSearch(int idx) {
		sql = "select * from user where idx = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper, idx);
	}

	@Override
	public int setUserUpdate(UserVo vo) {
		sql = "update user set pwd=?, name=?, age=?, gender=?, address=? where idx = ?";
		return jdbcTemplate.update(sql, vo.getPwd(), vo.getName(), vo.getAge(), vo.getGender(), vo.getAddress(),
				vo.getIdx());
	}

	// 회원 아이디 부분일치 검색 -1건 이상 검색
	@Override
	public List<UserVo> getUserSearchListOkGet(String mid) {
		sql = "select * from user where mid like ?";
		return jdbcTemplate.query(sql, rowMapper, "%" + mid + "%");
	}

	// 회원 아이디 완전일치 검색 - 1건만 검색 - part에 따른 검색 (part : mid/name/address)
	@Override
	public UserVo userSearchPart(String part, String content) {
		sql = "select * from user where " + part + " = ?";
		try {
			List<UserVo> vos = jdbcTemplate.query(sql, rowMapper, content); // 여러개의 값 List로 받기

			if (vos != null && !vos.isEmpty()) { // 결과가 있고 빈 값이 아니라면 
				return vos.get(0); //첫 번째 결과 반환
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<UserVo> serListSearchPart(String part) {
		sql = "select * from user order by " + part;
		return jdbcTemplate.query(sql, rowMapper);
	}
}
