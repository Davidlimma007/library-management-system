package com.davidlima.library.domain.repository;

import com.davidlima.library.domain.entity.Book;
import com.davidlima.library.domain.entity.Loan;
import com.davidlima.library.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    long countByUserAndActiveTrue(User user); //quantos empréstimos ativos o usuário tem

    boolean existsByBookAndActiveTrue(Book book); //verifica se o livro já está emprestado

    List<Loan> findByUserAndActiveTrue(User user); //lista empréstimos ativos do usuário
}
