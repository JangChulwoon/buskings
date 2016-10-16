package test;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.busking.bean.User;

import dao.UserDao;

public class dbtest {

	@Test
	public void Join() {
		/**
		 * login process 1. Input ID,Name, Pass, Location (password use BCrypt
		 * Algo..) 2. ID check (U_empty) 3. Success !
		 */
		ApplicationContext factory = new ClassPathXmlApplicationContext("xml/data.xml");
		String pass = BCrypt.hashpw("pass", BCrypt.gensalt());
		System.out.println(pass);
		User user = new User("jang", "name", pass, "location");
		UserDao dao = (UserDao) factory.getBean("dao");
		if (dao.U_empty(user.getId())) {
			dao.U_insert(user);
		} else {
			System.out.println("no_empty");
		}
	}

	@Test
	public void Login() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("xml/data.xml");
		UserDao dao = (UserDao) factory.getBean("dao");
		User user = dao.U_select("jang");
		boolean passcheck = BCrypt.checkpw("pass", user.getPass());
		if (user != null && user.getId().equals("jang") && passcheck) {
			System.out.println("ok");
		} else {
			// id or pass different input value
			System.out.println("no");
		}

	}

}
