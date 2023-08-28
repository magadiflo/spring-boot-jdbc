package com.magadiflo.spring.data.jdbc.app.entities.dto;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import com.magadiflo.spring.data.jdbc.app.entities.Task;

public record TaskDetails(Task task, Owner owner) {
}
