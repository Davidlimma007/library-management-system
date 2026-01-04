package com.davidlima.library.domain.entity;

import com.davidlima.library.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Evita bugs, Mudança de ordem do Enum, Dado quebrado no banco de dados
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedby = "user") //Dono da relação será Loan, evita tabela intermediária desnecessária
    private List<Loan> loans = new ArrayList<>();
}
