package com.magadiflo.spring.data.jdbc.app.entities.dto;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import com.magadiflo.spring.data.jdbc.app.entities.Task;

import java.util.List;

public record OwnerDetails(Owner owner, List<Task> tasks) {
}
