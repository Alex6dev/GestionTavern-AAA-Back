package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class ManagerRepositoryTest {
	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	@Sql("givenManagerAndPlayer_findByIdPlayer_thenReturnManager.sql")
	public void givenManagerAndPlayer_findByIdPlayer_thenReturnManager() {
		Optional<Player> playerOpt = playerRepository.findById(2);
		Player player = playerOpt.get();
		List<Manager> managers = managerRepository.findByPlayer(player);
		assertEquals(managers.size(), 3);
	}

	@Test
	@Sql("givenManagerAndPlayer_findByIdPlayer_thenReturnAnEmptyList.sql")
	public void givenManagerAndPlayer_findByIdPlayer_thenReturnAnEmptyList() {
		Optional<Player> playerOpt = playerRepository.findById(2);
		Player player = playerOpt.get();
		List<Manager> managers = managerRepository.findByPlayer(player);
		assertTrue(managers.isEmpty());
	}
}
