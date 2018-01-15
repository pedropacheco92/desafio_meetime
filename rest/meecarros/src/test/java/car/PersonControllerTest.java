package car;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.meecarros.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllPersons() throws Exception {
		String token = "7b89e5ee230957c0971499e1c502fc18e0e23c89";
		this.mockMvc.perform(get("/v1/persons/" + token)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testGetAllPersonsFail() throws Exception {
		String token = "asdas";
		this.mockMvc.perform(get("/v1/persons/" + token)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());
	}

}
