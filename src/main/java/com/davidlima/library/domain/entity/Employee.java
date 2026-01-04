package com.davidlima.library.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //Identifica funcionário internamente
    @Column(nullable = false, unique = true)
    private String registrationCode;

    @Column(nullable = false, unique = true)
    private String email;

    // ================= RELATIONSHIPS =================

    @OneToMany(mappedBy = "employee")
    private List<Loan> loans = new ArrayList<>();
}
