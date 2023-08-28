package com.magadiflo.spring.data.jdbc.app.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "tutorials")
public class Tutorial {
    @Id
    private Integer id;
    private String title;
}
