package com.davidlima.library.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn; // código numérico único que identifica um livro ou publicação monográfica

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(nullable = false)
    private Boolean available = true;

    //book é o dono da relação
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<>();
}
