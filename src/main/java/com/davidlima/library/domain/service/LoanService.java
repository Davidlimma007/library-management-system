package com.davidlima.library.domain.service;

import com.davidlima.library.domain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
}
