package com.example.hrms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team_Name")
    private String teamName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_fid", referencedColumnName = "id")
    List<Employee> employees = new ArrayList<>();

}
