package com.example.astonsecondhomework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Group entity
 * <p>
 * Relation:
 * One-to-Many: Group -> Students
 */
@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group implements com.example.astonsecondhomework.entity.global.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "course")
    private int course;

    @Column(name = "semester")
    private int semester;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
