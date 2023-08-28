package com.magadiflo.spring.data.jdbc.app.entities.dto;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import com.magadiflo.spring.data.jdbc.app.entities.User;

import java.util.List;

public record UserDetails(User user, List<Authority> authorities) {
}
