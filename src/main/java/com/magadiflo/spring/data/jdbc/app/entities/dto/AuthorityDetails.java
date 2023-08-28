package com.magadiflo.spring.data.jdbc.app.entities.dto;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import com.magadiflo.spring.data.jdbc.app.entities.User;

import java.util.List;

public record AuthorityDetails(Authority authority, List<User> users) {
}
