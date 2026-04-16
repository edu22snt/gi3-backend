package com.br.gi3.model;

import com.br.gi3.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Role.class);

        Role role1 = new Role();
        Role role2 = new Role();

        role1.setNome("nome role 1");
        role2.setNome(role1.getNome());

        assertThat(role1).isEqualTo(role2);

        role2.setNome("nome role 2");
        assertThat(role1).isNotEqualTo(role2);
    }

}