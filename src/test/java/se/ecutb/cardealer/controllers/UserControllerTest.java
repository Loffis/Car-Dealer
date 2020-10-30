package se.ecutb.cardealer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.ecutb.cardealer.entities.User;
import se.ecutb.cardealer.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@WebMvcTest
@AutoConfigureRestDocs(uriPort = 7000)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;

    void findAllUsers() throw Exception {
        given(userRepository.findAll()).willReturn(List.of(User.builder().build()));

        mockMvc.perform(get("/api/v1/users/{id}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/users-get-one",
                        path-))
    }

}


 */