package com.mp.todo.domain;

import com.mp.todo.domain.exception.BadNameException;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;



    public Task() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) throws BadNameException {
        if (newName.isEmpty())
        {
            throw new BadNameException("Task name should not be empty");
        }
        name = newName;
    }
}