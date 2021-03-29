package com.migros.couriertracker;

import com.migros.couriertracker.data.entity.CourierLocation;
import com.migros.couriertracker.service.CourierManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith({RestDocumentationExtension.class,SpringExtension.class, MockitoExtension.class})
class CourierTrackerApplicationTests {
	private MockMvc mockMvc;

	@MockBean
	private CourierManager service;

	@BeforeEach
	public void setUp(WebApplicationContext context,
	                  RestDocumentationContextProvider restDocumentation) {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(documentationConfiguration(restDocumentation)
						.snippets()
						.withEncoding("UTF-8"))
				.build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void createCourier_valid() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/couriers")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\": \"ahmet\" }") ;

		mockMvc.perform(builder)
				.andExpect(status().isOk())
				.andDo(document("create_courier_valid"));
	}


	@Test
	void courierLocation_valid() throws Exception {
		ZonedDateTime date = ZonedDateTime.now();
		Double lat = 40.9632463;
		Double lng = 29.0630908;
		Long id = 1L;
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/couriers/locations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"courierId\": \""+id+"\", \"lat\": "+lat+", \"lng\": \""+lng+"\", \"date\" : \"" + date + "\" }") ;

		given(service.addCourierLocation(anyLong(), anyDouble(), anyDouble(), any())).willReturn(new CourierLocation(lat, lng, date, "Caddebostan MMM Migros", true));

		mockMvc.perform(builder)
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andDo(document("courier_add_location_valid"));
	}

	@Test
	void getCourierTotalTravelDistance_valid() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/couriers/5/travelDistance")
				.contentType(MediaType.APPLICATION_JSON);


		given(service.getTotalDistanceTraveled(5L)).willReturn(50.0);

		mockMvc.perform(builder)
				.andExpect(status().isOk())
				.andDo(document("courier_total_travel_distance_valid"));
	}

}
