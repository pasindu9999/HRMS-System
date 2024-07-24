package com.example.hrms.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "departments")

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_fid", referencedColumnName = "id")
    List<Employee> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_fid", referencedColumnName = "id")
    List<Team> teams = new ArrayList<>();


}
