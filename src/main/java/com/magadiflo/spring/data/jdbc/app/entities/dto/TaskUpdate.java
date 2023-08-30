package com.magadiflo.spring.data.jdbc.app.entities.dto;

import com.magadiflo.spring.data.jdbc.app.entities.Comment;

import java.util.Set;

public record TaskUpdate(Integer ownerId, String title, String content, Set<Comment> comments) {
}
