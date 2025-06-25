package com.sanket.tender_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String companyName;

    private String email;

    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    public Boolean isApprover() {
        return role.getRolename().equals("APPROVER");
    }
}
