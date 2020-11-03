package se.ecutb.cardealer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;
import se.ecutb.cardealer.entities.User;
import se.ecutb.cardealer.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs(uriPort = 7000)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;


    @Test
    void findAllUsers() throws Exception {
        given(userRepository.findAll()).willReturn(List.of(User.builder().build()));

        mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("/api/v1/users-get-all"));


    }

    @Test
    void findUserById() throws Exception {
        given(userRepository.findById(any())).willReturn(Optional.of(User.builder().build()));

        mockMvc.perform(get("/api/v1/users/{id}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/users-get-one",
                        pathParameters(
                                parameterWithName("id").description("UUID string of user to get.")
                        ),
                        responseFields(
                                fieldWithPath("id").description("User id"),
                                fieldWithPath("name").description("User's first and last name"),
                                fieldWithPath("birthday").description("Birthday").type(LocalDate.class),
                                fieldWithPath("username").description("Username"),
                                fieldWithPath("password").description("Password"),
                                fieldWithPath("email").description("Email"),
                                fieldWithPath("phonenumber").description("Phone number"),
                                fieldWithPath("acl").description("Access Control List")
                        )));
    }

    @Test
    void saveUser() throws Exception {
        User testUser = User.builder()
                .name("Arne Anka")
                .birthday(LocalDate.parse("1990-01-01"))
                .username("ankan")
                .password("1234")
                .email("arne@anka.com")
                .phonenumber("010-123456")
                .acl(Collections.singletonList("TESTUSER"))
                .build();

        String userJson = objectMapper.writeValueAsString(testUser);

        given(userRepository.save(any())).willReturn(User.builder().build());

        ConstrainedFields fields = new ConstrainedFields(User.class);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/users-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("name").description("User's first and last name"),
                                fields.withPath("birthday").description("Birthday").type(LocalDate.class),
                                fields.withPath("username").description("Username"),
                                fields.withPath("password").description("Password"),
                                fields.withPath("email").description("Email"),
                                fields.withPath("phonenumber").description("Phone number"),
                                fields.withPath("acl").description("Access Control List")
                        ),
                        responseFields(
                                fieldWithPath("id").description("User id"),
                                fieldWithPath("name").description("User's first and last name"),
                                fieldWithPath("birthday").description("Birthday").type(LocalDate.class),
                                fieldWithPath("username").description("Username"),
                                fieldWithPath("password").description("Password"),
                                fieldWithPath("email").description("Email"),
                                fieldWithPath("phonenumber").description("Phone number"),
                                fieldWithPath("acl").description("Access Control List")
                        )));
    }

    @Test
    void updateUser() throws Exception {
        User testUser = User.builder()
                .name("Arne Anka")
                .birthday(LocalDate.parse("1990-01-01"))
                .username("ankan")
                .password("1234")
                .email("arne@anka.com")
                .phonenumber("010-123456")
                .acl(Collections.singletonList("TESTUSER"))
                .build();

        String userJson = objectMapper.writeValueAsString(testUser);

        ConstrainedFields fields = new ConstrainedFields(User.class);

        mockMvc.perform(put("/api/v1/users/{id}", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isNoContent())
                .andDo(document("v1/users-update",
                        pathParameters(
                                parameterWithName("id").description("UUID of user to update.")
                        ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("name").description("User's first and last name"),
                                fields.withPath("birthday").description("Birthday").type(LocalDate.class),
                                fields.withPath("username").description("Username"),
                                fields.withPath("password").description("Password"),
                                fields.withPath("email").description("Email"),
                                fields.withPath("phonenumber").description("Phone number"),
                                fields.withPath("acl").description("Access Control List")
                        )));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/{id}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(document("v1/users-delete",
                        pathParameters(
                                parameterWithName("id").description("UUID of user to delete.")
                        )));
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}