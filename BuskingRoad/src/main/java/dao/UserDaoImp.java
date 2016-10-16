package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.busking.bean.User;

public interface UserDaoImp {

	public int count();

	public void U_insert(User user);

	public boolean U_empty(String id);

	public User U_select(String id);
}
