package com.gestone.gestone_api.user;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.user.User;
import com.gestone.gestone_api.domain.user.UserType;

import static org.assertj.core.api.Assertions.*;

public class UserTest {
    @Test
    public void testConstructorAndGetters() {
        UUID id = UUID.randomUUID();
        String name = "Beltrandes";
        String email = "beltrandes@beltrandes.com";
        String password = "beltrandes123";
        String phone = "1234567890";
        UserType type = UserType.ADMIN;
        Marbleshop marbleshop = new Marbleshop();

        User user = new User(id, name, email, password, phone, type, marbleshop);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getPhone()).isEqualTo(phone);
        assertThat(user.getType()).isEqualTo(type);
        assertThat(user.getMarbleshop()).isEqualTo(marbleshop);
    }
}
