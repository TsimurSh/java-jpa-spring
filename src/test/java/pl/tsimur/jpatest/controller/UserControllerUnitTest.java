package pl.tsimur.jpatest.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.tsimur.jpatest.model.Tariff;
import pl.tsimur.jpatest.model.dto.UserMiniDto;
import pl.tsimur.jpatest.service.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerUnitTest {
    public static final UserMiniDto USER = new UserMiniDto(-1, "Tsima", "Batman", 33);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;

    @Test
    @Disabled("DO 🚁🚁🚁🚁🚁🚁🚁🚁🚁")
    void registrationTest() throws Exception {
        //  (objectMapper):
        // String jsonRequest = objectMapper.writeValueAsString(loginDto);
        // { "key" : "value" }


    }



    @Test
    void getAllUsersTest() throws Exception {
        Mockito.when(service.findAllMiniUsers()) // TODO: What are other  `when` & `doNothing` ?
                .thenReturn(
                        List.of(USER)
                );

        mockMvc.perform(
                        get("/user")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())

                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Tsima"))
                .andExpect(jsonPath("$[0].surname").value("Batman"))
                .andExpect(jsonPath("$[0].balance").doesNotExist())
                .andExpect(jsonPath("$[0].email").doesNotExist())
                .andExpect(jsonPath("$[0].password").doesNotExist())
        ;
    }

    @Test
    void buySubscriptionTest() throws Exception {
        Mockito.doNothing()
                .when(service).buySubscription(
                        isA(Integer.class), isA(Tariff.class) // TODO: what beside `isA()` ?
                );

        mockMvc.perform(put("/user/-3/INDIVIDUAL"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
