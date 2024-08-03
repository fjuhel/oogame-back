// package com.ogame.core.api;

// import static org.hamcrest.Matchers.is;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ogame.core.domain.User;
// import com.ogame.core.repository.UserRepository;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class UserApiTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @BeforeEach
//     public void setUp() {
//         userRepository.deleteAll();
//     }

//     @Test
//     public void shouldCreateUser() throws Exception {
//         User user = new User("testuser", passwordEncoder.encode("testpassword"));

//         mockMvc.perform(post("/api/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(user)))
//                 .andExpect(status().isOk());

//         User createdUser = userRepository.findByUsername("testuser").orElse(null);
//         assert createdUser != null;
//         assert createdUser.getUsername().equals("testuser");
//         assert passwordEncoder.matches("testpassword", createdUser.getPassword());
//     }

//     @Test
//     public void shouldReturnUserDetails() throws Exception {
//         User user = new User("testuser", passwordEncoder.encode("testpassword"));
//         user.getRoles().add("ROLE_USER");
//         userRepository.save(user);

//         mockMvc.perform(get("/api/user")
//                 .param("username", "testuser")
//                 .with(httpBasic("testuser", "testpassword")))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.username", is("testuser")));
//     }
// }
