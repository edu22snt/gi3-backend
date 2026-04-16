package com.br.gi3.service.dto;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UsuarioDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuarioDTO.class);

        UsuarioDTO usuarioDTO1 = new UsuarioDTO();
        UsuarioDTO usuarioDTO2 = new UsuarioDTO();

        usuarioDTO1.setId(1L);
        usuarioDTO2.setId(usuarioDTO1.getId());

        usuarioDTO1.setUsername("user");
        usuarioDTO1.setPassword("123456");

        assertThat(usuarioDTO1).isEqualTo(usuarioDTO2);

        usuarioDTO2.setId(2L);
        assertThat(usuarioDTO1).isNotEqualTo(usuarioDTO2);
    }

}