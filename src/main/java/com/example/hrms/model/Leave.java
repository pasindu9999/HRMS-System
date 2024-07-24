package com.example.hrms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee user;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "requested_leave_date")
    private Date requestedLeaveDate;

    @Column(name = "date_requested")
    private Date dateRequested;

}
