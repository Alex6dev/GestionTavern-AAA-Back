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

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityNotFoundException;

import aaa.tavern.dto.received.ShopIngredientDto;
import aaa.tavern.dto.received.ShopIngredientQuantityDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ShopControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShopService shopService;

	//////// TEST BYING /////////
	@Test
	public void GivenIngredientAndManager_whenPostBuing_ThenPrepareIngredientAndShopServiceCalled() throws Exception {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);
	
		ObjectMapper objectMapper = new ObjectMapper();

		ShopIngredientDto jsonDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopBying")
				.contentType("application/json")
				.content(body);

		mockMvc.perform(query);

		Mockito.verify(shopService).prepareIngredientAndBuy(shopIngredientDto);
	}

	@Test
	public void GivenNotParam_whenPostBuing_ThenPrepareIngredientAndBuyServiceNotCalled() throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopBying");

		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 400);
	}

	@Test

	public void GivenIngredientAndManager_whenMockToReturnEntityNotFoundExceptionForBying_ThenReturnError406()
			throws Exception {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);
		ObjectMapper objectMapper = new ObjectMapper();

		ShopIngredientDto jsonDto = new ShopIngredientDto(1,tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopBying")
				.contentType("application/json")
				.content(body);

		Mockito.doThrow(EntityNotFoundException.class).when(shopService).prepareIngredientAndBuy(shopIngredientDto);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	@Test

	public void GivenIngredientAndManager_whenMockToReturnForbiddenException_ThenReturnError406() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		ShopIngredientDto jsonDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopBying")
				.contentType("application/json")
				.content(body);

		Mockito.doThrow(ForbiddenException.class).when(shopService).prepareIngredientAndBuy(shopIngredientDto);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	////////// SELLING ////////////

	@Test
	public void GivenIngredientAndManager_whenPostSelling_ThenPrepareIngredientAndShopServiceCalled() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		ShopIngredientDto jsonDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopSelling")
				.contentType("application/json")
				.content(body);

		mockMvc.perform(query);

		Mockito.verify(shopService).prepareIngredientAndSell(shopIngredientDto);
	}

	@Test
	public void GivenNotParamForSelling_whenPostSelling_ThenPrepareIngredientAndBuyServiceNotCalled() throws Exception {
		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopSelling");

		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 400);
	}

	@Test

	public void GivenIngredientAndManager_whenMockToReturnEntityNotFoundExceptionForPostSelling_ThenReturnError406()
			throws Exception {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		ObjectMapper objectMapper = new ObjectMapper();
		
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		ShopIngredientDto jsonDto = new ShopIngredientDto(1,tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopSelling")
				.contentType("application/json")
				.content(body);

		Mockito.doThrow(EntityNotFoundException.class).when(shopService).prepareIngredientAndSell(shopIngredientDto);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}

	@Test

	public void GivenIngredientAndManager_whenMockToReturnForbiddenExceptionForPostSelling_ThenReturnError406()
			throws Exception {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);
		ObjectMapper objectMapper = new ObjectMapper();

		ShopIngredientDto jsonDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		String body = objectMapper
				.valueToTree(jsonDto)
				.toPrettyString();

		MockHttpServletRequestBuilder query = MockMvcRequestBuilders
				.post("/api/game/shop/ShopSelling")
				.contentType("application/json")
				.content(body);

		Mockito.doThrow(ForbiddenException.class).when(shopService).prepareIngredientAndSell(shopIngredientDto);
		mockMvc.perform(query);

		int status = mockMvc
				.perform(query)
				.andReturn()
				.getResponse()
				.getStatus();

		assertEquals(status, 406);
	}
}
