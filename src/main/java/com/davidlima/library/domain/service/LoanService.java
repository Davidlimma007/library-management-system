package com.davidlima.library.domain.service;

import com.davidlima.library.domain.entity.Book;
import com.davidlima.library.domain.entity.Employee;
import com.davidlima.library.domain.entity.Loan;
import com.davidlima.library.domain.entity.User;
import com.davidlima.library.domain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;


    public Loan loanbook (User user, Book book, Employee employee){

        // RULE 1: Book already borrowed?
        if(loanRepository.existsByBookAndActiveTrue(book)){
            throw new RuntimeException("Book is already loaned");
        }

        // RULE 2: Does the user already have 2 active loans?
        long activeLoans = loanRepository.countByUserAndActiveTrue(user); //conta no banco quantos empréstimos tem

        if(activeLoans >=2){
            throw new RuntimeException("User already has 2 active loans");
        }

            LocalDate today = LocalDate.now();
            LocalDate dueDate = today.plusDays(15); //somas os dias para renovação do empréstimo

            // Cria o objeto via Lombok com o .builder() e ela precisa dos campos obrigatórios da entidade
            Loan loan = Loan.builder()
                    .user(user)
                    .book(book)
                    .employee(employee)
                    .loanDate(today)
                    .dueDate(dueDate)
                    .renewalCount(0)
                    .active(true)
                    .build();

            return loanRepository.save(loan);

    }
}
