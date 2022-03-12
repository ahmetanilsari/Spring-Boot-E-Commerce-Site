package com.shopping.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopping.common.entity.Role;
import com.shopping.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userAhmet = new User("ahmet.sari@sahabt.com","admin123","Ahmet Anil", "Sari");
		userAhmet.addRole(roleAdmin);
		
		User savedUser = repo.save(userAhmet);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userMerve = new User ("merve.gundogmus@sahabt.com","admin123","Merve","Gundogmus");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userMerve.addRole(roleEditor);
		userMerve.addRole(roleAssistant);
		
		User savedUser = repo.save(userMerve);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user-> System.out.println(user));	
	}
	
	@Test
	public void testGetUserById() {
		User userAhmet = repo.findById(1).get();
		System.err.println(userAhmet);
		assertThat(userAhmet).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userAhmet = repo.findById(1).get();
		userAhmet.setEnabled(true);
		userAhmet.setEmail("ahmet.sari1@sahabt.com");
		
		repo.save(userAhmet);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userMerve = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userMerve.getRoles().remove(roleEditor);
		userMerve.addRole(roleSalesperson);
		
		repo.save(userMerve);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);		
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "kazim@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
}
