/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package car;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.meecarros.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CarControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllCars() throws Exception {
		this.mockMvc.perform(get("/v1/carros")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testGetCarFail() throws Exception {
		this.mockMvc.perform(get("/v1/carros/1")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist()); // nao tem carro cadastrado ainda
	}

	@Test
	public void testSaveCar() throws Exception {
		String json = "{ \"id\": 0, \"modelo\": \"modeloX\", \"cor\": \"Branco\", \"ano\": \"2015\", \"personId\": 1 }";

		this.mockMvc.perform(post("/v1/carros/0")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());
	}

	@Test
	public void testEditCar() throws Exception {
		String json = "{ \"id\": 1, \"modelo\": \"modeloX\", \"cor\": \"Branco\", \"ano\": \"2010\", \"personId\": 1 }";

		this.mockMvc.perform(put("/v1/carros/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());
	}

	@Test
	public void testDeleteCar() throws Exception {
		this.mockMvc.perform(delete("/v1/carros/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());
	}

}
