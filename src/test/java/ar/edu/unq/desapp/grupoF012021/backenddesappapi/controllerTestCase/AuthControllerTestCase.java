package ar.edu.unq.desapp.grupoF012021.backenddesappapi.controllerTestCase;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.AuthService;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.webservice.AuthController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTestCase {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthService service;

    @Test
    public void givenUser_whenGetUser_thenReturnUserName() throws Exception
    {
        /*
        EXAMPLE:
        
        User userTest = new User();

        List<Employee> allEmployees = Arrays.asList(alex);

        given(service.getAllEmployees()).willReturn(allEmployees);

        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(alex.getName())));
         */
    }
}
