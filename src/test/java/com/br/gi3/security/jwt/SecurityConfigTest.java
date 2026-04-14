package com.br.gi3.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
    void shouldBePermittedForAll() throws Exception {
        mockMvc.perform(post("/api/authenticate"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldBePermittedForUserRoleFindBbyId() throws Exception {
        mockMvc.perform(get("/api/usuario/findById/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRoleFindBbyId() throws Exception {
        mockMvc.perform(get("/api/usuario/findById/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldBePermittedForUserRoleFindAll() throws Exception {
        mockMvc.perform(get("/api/usuario/findAll"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRoleFindAll() throws Exception {
        mockMvc.perform(get("/api/usuario/findAll"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRole() throws Exception {
        mockMvc.perform(post("/api/usuario/save"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRoleDelete() throws Exception {
        mockMvc.perform(delete("/api/usuario/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateEndpoint_shouldBePermittedForAdminRoleUpdate() throws Exception {
        mockMvc.perform(put("/api/usuario/update"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRolePrestacaoServicoSave() throws Exception {
        mockMvc.perform(post("/api/prestacaoServico/save"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldBePermittedForUserRolePrestacaoServicoRepasse() throws Exception {
        mockMvc.perform(get("/api/prestacaoServico/repasse"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBePermittedForAdminRolePrestacaoServicoRepasse() throws Exception {
        mockMvc.perform(get("/api/prestacaoServico/repasse"))
                .andExpect(status().isOk());
    }

//    @Test
//    void shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(get("/api/usuario/findById/1"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    void findAllEndpoint_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(get("/api/usuario/findAll"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser(roles = "USER")
//    void saveEndpoint_shouldBeForbiddenForUserRole() throws Exception {
//        mockMvc.perform(post("/api/usuario/save"))
//                .andExpect(status().isForbidden()); // 403 Forbidden
//    }

//    @Test
//    void saveEndpoint_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(post("/api/usuario/save"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser(roles = "USER")
//    void deleteEndpoint_shouldBeForbiddenForUserRole() throws Exception {
//        mockMvc.perform(delete("/api/usuario/delete/1"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    void deleteEndpoint_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(delete("/api/usuario/delete/1"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser(roles = "USER")
//    void updateEndpoint_shouldBeForbiddenForUserRole() throws Exception {
//        mockMvc.perform(put("/api/usuario/update"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    void updateEndpoint_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(put("/api/usuario/update"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    @WithMockUser(roles = "USER")
//    void prestacaoServicoSave_shouldBeForbiddenForUserRole() throws Exception {
//        mockMvc.perform(post("/api/prestacaoServico/save"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    void prestacaoServicoSave_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(post("/api/prestacaoServico/save"))
//                .andExpect(status().isUnauthorized());
//    }

//    @Test
//    void prestacaoServicoRepasse_shouldBeForbiddenForUnauthenticated() throws Exception {
//        mockMvc.perform(get("/api/prestacaoServico/repasse"))
//                .andExpect(status().isUnauthorized());
//    }
}