package com.example.hrms.controller;

import com.example.hrms.exception.ResourceNotFoundException;
import com.example.hrms.model.Team;
import com.example.hrms.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hrms/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams(){

        return teamRepository.findAll();
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team){

        return teamRepository.save(team);
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable long id){
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not exist with id "+ id));
        return ResponseEntity.ok(team);
    }

    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable long id, @RequestBody Team teamDetails){
        Team updateTeam = teamRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Team not exist with id: "+id));

        updateTeam.setTeamName(teamDetails.getTeamName());
        updateTeam.setEmployees(teamDetails.getEmployees());

        teamRepository.save(updateTeam);

        return ResponseEntity.ok(updateTeam);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable long id){
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Team not exist with id "+ id));

        teamRepository.delete(team);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
