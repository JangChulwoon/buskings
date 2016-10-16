package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.busking.bean.User;

public class UserDao implements UserDaoImp {

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void U_insert(User user) {
		template.update("insert into customer(id, name,pass,category) values(?, ?,?,?)",
				new Object[] { user.getId(), user.getName(), user.getPass(), user.getLocation() });

	}

	@Override
	public boolean U_empty(String id) {
		// TODO Auto-generated method stub
		int check = template.queryForInt("select count(*) from customer where id =?", new Object[] { id });
		return (check == 0);
	}

	@Override
	public User U_select(String id) {
		// TODO Auto-generated method stub
		try {
			return (User) template.queryForObject("select * from customer where id = ?", new Object[] { id },
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowCnt) throws SQLException {
							User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("pass"),
									rs.getString("category"));
							return user;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public int count() {
		return template.queryForInt("select count(*) from customer");
	}

}