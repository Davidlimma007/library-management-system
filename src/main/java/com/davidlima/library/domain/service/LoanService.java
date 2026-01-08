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

    //empréstimo de um livro
    public Loan loanbook (User user, Book book, Employee employee){


        // REGRA 1: O livro já está emprestado?
        if(loanRepository.existsByBookAndActiveTrue(book)){
            throw new RuntimeException("O livro já está emprestado.");
        }

        // REGRA 2: O usuário já possui 2 empréstimos ativos?
        long activeLoans = loanRepository.countByUserAndActiveTrue(user); //conta no banco quantos empréstimos tem

        // REGRA 3: o usuário já possui 2 empréstimos ativos?
        if(activeLoans >=2){
            throw new RuntimeException("O usuário já possui 2 empréstimos ativos.");
        }

            //define a data do empréstimo
            LocalDate today = LocalDate.now();
            //somas os dias para renovação do empréstimo
            LocalDate dueDate = today.plusDays(15);

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

    //encerramento do empréstimo
    public Loan returnBook(Book book){
        //busca o empréstimo pelo id
        Loan loan = loanRepository.findByBookAndActiveTrue(book)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado nenhum empréstimo ativo para este livro."));

        //redefine o empréstimo como inativo
        loan.setActive(false);
        //define a data de retorno
        loan.setReturnDate(LocalDate.now());

        return loanRepository.save(loan);
    }

    //renovação do empréstimo
    public Loan renewLoan(Long loanId){
        //busca o empréstimo pelo id
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        // Regra 1: saber se o livro foi devolvido
        if(!loan.getActive()){
            throw new RuntimeException("Não é possível renovar um empréstimo devolvido.\n");
        }

        //Regra 2: saber se o livro já foi renovado 2 vezes
        if(loan.getRenewalCount() >= 2){
            throw new RuntimeException("Número máximo de renovações atingido");
        }

        //adiciona mais 15 dias
        loan.setDueDate(loan.getDueDate().plusDays(15));
        //incrementa a renovação
        loan.setRenewalCount(loan.getRenewalCount() + 1);

        return loanRepository.save(loan);
    }

}
