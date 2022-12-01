package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Player;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class PlayerRepositoryTest {
	@Autowired
	private PlayerRepository playerRepository;

	@Test
	@Sql("givenPlayer_findByEmail_thenReturnPlayer.sql")
	public void givenPlayer_findByEmail_thenReturnPlayer() {
		Optional<Player> playerOpt = playerRepository.findByEmail("Test2@test.com");
		Player player = playerOpt.get();
		assertEquals(player.getEmail(), "Test2@test.com");
	}

	@Test
	public void givenPlayer_findByEmail_thenReturnNothing() {
		Optional<Player> playerOpt = playerRepository.findByEmail("Test2@test.com");
		assertTrue(playerOpt.isEmpty());
	}

	@Test
	@Sql("givenPlayer_findByNickname_thenReturnPlayer.sql")
	public void givenPlayer_findByNickname_thenReturnPlayer() {
		Optional<Player> playerOpt = playerRepository.findByNickname("Test3");
		Player player = playerOpt.get();
		assertEquals(player.getNickname(), "Test3");
	}

	@Test
	public void givenPlayer_findByNickname_thenReturnNothing() {
		Optional<Player> playerOpt = playerRepository.findByNickname("Test3");
		assertTrue(playerOpt.isEmpty());
	}
}
