package com.example.hrms.controller;

import com.example.hrms.exception.LeaveNotFoundException;
import com.example.hrms.model.Leave;
import com.example.hrms.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hrms/leaves")
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepository;

    @GetMapping("get-leaves")
    public List<Leave> getAllLeaves(){
        return leaveRepository.findAll();
    }

    @PostMapping("add-leaves")
    public Leave createLeaveRequest(@RequestBody Leave leave){
        return leaveRepository.save(leave);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable int id,@RequestBody Leave updatedLeaveDetails){
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new LeaveNotFoundException("Leave does not exit with id: " + id));

        leave.setReason(updatedLeaveDetails.getReason());
        leave.setUser(updatedLeaveDetails.getUser());
        leave.setApprovedBy(updatedLeaveDetails.getApprovedBy());
        leave.setApproved(updatedLeaveDetails.isApproved());
        leave.setRequestedLeaveDate((updatedLeaveDetails.getRequestedLeaveDate()));
        leave.setDateRequested(updatedLeaveDetails.getDateRequested());

        leaveRepository.save(updatedLeaveDetails);
        return ResponseEntity.ok(leave);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteLeaveRequest(@PathVariable int id){
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new LeaveNotFoundException("Leave does not exit with id: " + id));

        leaveRepository.delete(leave);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
