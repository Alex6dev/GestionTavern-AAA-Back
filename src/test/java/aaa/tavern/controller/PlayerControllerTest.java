package aaa.tavern.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.service.PlayerService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    // TODO Revoir le Test, problème pour tester le dto
    public void GivenRegister_whenPlayer_ThenSuccessCreated() throws Exception {
        MultiValueMap<String, String> player = new LinkedMultiValueMap<String, String>();
        player.add("email", "heyman591@hotmail.com");
        player.add("nickname", "monpseudotype");
        player.add("password", "iiiiiiii");
        player.add("matchingPassword", "iiiiiiii");

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/register")
                .params(player);

        mockMvc.perform(query);

        PlayerDto playerDto = new PlayerDto("heyman591@hotmail.com", "monpseudotype", "iiiiiiii", "iiiiiiii");

        Mockito.verify(playerService).createPlayer(playerDto);
    }

    @Test
    public void GivenLogin_whenPlayer_ThenSuccess() throws Exception {
        PlayerDto playerDto = new PlayerDto("grimir59@gmail.com", "monpseudotype", "iiiiiiii", "iiiiiiii");
        playerService.createPlayer(playerDto);

        MockHttpServletRequestBuilder query = MockMvcRequestBuilders
                .post("/login")
                .param("email", "grimir59@gmail.com")
                .param("password", "iiiiiiii");

        mockMvc.perform(query);

    }
}
