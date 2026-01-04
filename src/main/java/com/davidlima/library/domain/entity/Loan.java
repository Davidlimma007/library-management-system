package com.davidlima.library.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ================= RELATIONSHIPS =================

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // ================= DATES =================

    @Column(nullable = false)
    private LocalDate loanDate; //data que o empréstimo foi feito

    @Column(nullable = false)
    private LocalDate dueDate; //

    private LocalDate returnDate;

    // ================= BUSINESS RULES =================

    @Column(nullable = false)
    private Integer renewalCount = 0;

    @Column(nullable = false)
    private Boolean active = true;

}
