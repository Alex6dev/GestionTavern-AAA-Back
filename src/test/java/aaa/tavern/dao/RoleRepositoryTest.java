package aaa.tavern.dao;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import aaa.tavern.entity.Role;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class RoleRepositoryTest {
	@Autowired
	private RoleRepository roleRepository;
	// findByName

	@Test
	@Sql("givenRole_findByName_thenReturnRole.sql")
	public void givenRole_findByName_thenReturnRole() {
		Optional<Role> roleOpt = roleRepository.findByName("Test4");
		Role role = roleOpt.get();
		assertEquals(role.getName(), "Test4");
	}

	@Test
	public void givenRole_findByName_thenReturnNothing() {
		Optional<Role> roleOpt = roleRepository.findByName("Test4");
		assertTrue(roleOpt.isEmpty());
	}
}
