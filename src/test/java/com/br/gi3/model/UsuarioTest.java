package com.br.gi3.model;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UsuarioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usuario.class);

        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        usuario1.setId(1L);
        usuario2.setId(usuario1.getId());

        usuario1.setUsername("user");
        usuario1.setPassword("123456");
        usuario1.setRoles(Set.of(new Role("ROLE_ADMIN")));

        assertThat(usuario1).isEqualTo(usuario2);

        usuario2.setId(2L);
        assertThat(usuario1).isNotEqualTo(usuario2);
    }

}