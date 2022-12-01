package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dao.RoleRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.entity.Role;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class PlayerServiceTest {
	@MockBean
	PlayerRepository userRepository;

	@Autowired
	PlayerService userService;

	@MockBean
	RoleRepository roleRepository;

	@Test
	void givenNewPlayerDto_WhenValidPlayer_ThenAddSuccess(){
		Role Role = new Role("USER") ;
		Optional<Role> optRole = Optional.of(Role) ;

		PlayerDto playerDto = new PlayerDto("heyman.adrien@gmail.com", "nickname", "12345678", "12345678") ;
		
		Mockito.when(roleRepository.findByName("USER")).thenReturn(optRole);
		userService.createPlayer(playerDto) ;

		Mockito.verify(userRepository).save(ArgumentMatchers.argThat(x->x.getNickname().equals(playerDto.getNickname())));
	}

	@Test
	void givenNewPlayerDto_WhenPlayerDtoIsNull_ThenNullPointerException(){
		Role Role = new Role("USER") ;
		Optional<Role> optRole = Optional.of(Role) ;

		PlayerDto playerDto = null ;
		
		Mockito.when(roleRepository.findByName("USER")).thenReturn(optRole);

		assertThrows(NullPointerException.class, ()->userService.createPlayer(playerDto));
	}

	@Test
	void givenNewPlayerrDto_WhenWithoutRole_ThenNoSuchElementException() {

		PlayerDto playerDto = new PlayerDto("heyman.adrien@gmail.com", "nickname", "12345678", "12345678") ;
		
		assertThrows(NoSuchElementException.class, ()->userService.createPlayer(playerDto));
	}
}
