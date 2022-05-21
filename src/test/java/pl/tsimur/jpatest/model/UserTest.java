package pl.tsimur.jpatest.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void createUser () throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setPassword("SECRET");
        System.out.println("User with id & password : " + user);

        String result = "{\"id\":1,\"email\":null,\"password\":\"SECRET\",\"name\":null,\"" +
                "surname\":null,\"balance\":null,\"age\":null,\"lastPayment\":null}";

        result += "";

        var json = mapper.writeValueAsString(user);

        assertEquals(result, json );
    }
}