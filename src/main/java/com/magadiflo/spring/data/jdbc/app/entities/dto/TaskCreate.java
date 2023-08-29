package com.magadiflo.spring.data.jdbc.app.entities.dto;

public record TaskCreate(Integer ownerId, String title, String content) {
}
