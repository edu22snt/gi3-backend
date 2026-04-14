package com.br.gi3.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfig.class, PasswordEncoderConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private MyUserDetailsService myUserDetailsService;

    @MockitoBean
    private JwtRequestFilter jwtRequestFilter;

    @Test
    public void authenticateEndpointPermitted() throws Exception {
        mockMvc.perform(post("/api/authenticate"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void adminEndpointAccess() throws Exception {
        mockMvc.perform(delete("/api/usuario/delete"))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(roles = {"USER"})
//    public void adminEndpointForbiddenForUser() throws Exception {
//        mockMvc.perform(delete("/api/usuario/delete"))
//                .andExpect(status().isForbidden());
//    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void userAndAdminEndpointAccessForUser() throws Exception {
        mockMvc.perform(get("/api/prestacaoServico/findAll"))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(roles = {"USER"})
//    public void protectedEndpointUnauthorized() throws Exception {
//        mockMvc.perform(put("/api/usuario/update"))
//                .andExpect(status().isUnauthorized());
//    }

}