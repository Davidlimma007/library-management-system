package com.davidlima.library.service;

import com.davidlima.library.domain.entity.funcao.Emprestimo;
import com.davidlima.library.domain.entity.funcao.Multa;
import com.davidlima.library.domain.entity.objeto.Livro;
import com.davidlima.library.domain.entity.pessoa.Funcionario;
import com.davidlima.library.domain.entity.pessoa.Usuario;
import com.davidlima.library.repository.funcao.EmprestimoRepository;
import com.davidlima.library.repository.funcao.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final MultaRepository multaRepository;


    //## 1 - EMPRÉSTIMO DO LIVRO
    public Emprestimo emprestimoLivro (Usuario usuario, Livro livro, Funcionario funcionario){

        // REGRA 0: USUÁRIO POSSUI MULTA EM ABERTO?
        if(multaRepository.existsByUsuarioAndPagaFalse(usuario)){
            throw new RuntimeException("Usuário possui multa em aberto.");
        }

        // REGRA 1: O LIVRO JÁ ESTÁ EMPRESTADO?
        if(emprestimoRepository.existsByLivroAndAtivoTrue(livro)){
            throw new RuntimeException("O livro já está emprestado.");
        }

        // REGRA 2: O USUÁRIO POSSUI 2 EMPRÉSTIMOS ATIVOS?
        long emprestimosAtivos = emprestimoRepository.countByUsuarioAndAtivoTrue(usuario); //CONTA NO BANCO QUANTOS EMPRESTIMOS ATIVOS TEM

        if(emprestimosAtivos >=2){
            throw new RuntimeException("O usuário já possui 2 empréstimos ativos.");
        }

        // SE PASSAR PELAS VALIDAÇÕES, REALIZA O EMPRÉSTIMO

            LocalDate hoje = LocalDate.now(); //DATA DE EMPRÉSTMO
            LocalDate dataVencimento = hoje.plusDays(15); //SOMA OS DIAS PARA DATA DE RETORNO NO EMPRÉSTIMO

            // CRIA O OBJETO VIA LOMBOK COM O .BUILDER() E ELA PRECISA DOS CAMPOS OBRIGATÓRIOS DA ENTIDADE
            Emprestimo emprestimo = Emprestimo.builder()
                    .usuario(usuario)
                    .livro(livro)
                    .funcionario(funcionario)
                    .dataEmprestimo(hoje)
                    .dataRetorno(dataVencimento)
                    .contagemRenovacao(0)
                    .ativo(true)
                    .build();

            return emprestimoRepository.save(emprestimo);

    }

    //## 2 - DEVOLUÇÃO DO LIVRO
    public Emprestimo devolucaoLivro (Livro livro){
        //BUSCA O EMPRÉSTIMO PELO ID
        Emprestimo emprestimo = emprestimoRepository.findByLivroAndAtivoTrue(livro)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado nenhum empréstimo ativo para este livro."));

        LocalDate hoje = LocalDate.now();
        BigDecimal multaFixa = BigDecimal.valueOf(20);
        BigDecimal valorPorDia = BigDecimal.valueOf(2);

        if(hoje.isAfter(emprestimo.getDataRetorno())){
            //DIAS DE ATRASO
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataRetorno(), hoje);
            // CALCULO DA MULTA POR DIAS DE ATRASO
            BigDecimal valorMulta = multaFixa.add(
                    valorPorDia.multiply(BigDecimal.valueOf(diasAtraso))
            );

            Multa multa = Multa.builder()
                    .usuario(emprestimo.getUsuario())
                    .emprestimo(emprestimo)
                    .quantidade(valorMulta)
                    .paga(false)
                    .criadoEm(hoje)
                    .build();

            multaRepository.save(multa);
        }

        emprestimo.setAtivo(false); //REDEFINE O EMPRÉSTIMO COMO INATIVO
        emprestimo.setDataRetorno(LocalDate.now()); //DEFINE A DATA DE RETORNO

        return emprestimoRepository.save(emprestimo);
    }

    //## 3 - RENOVAÇÃO DO EMPRÉSTIMO
    public Emprestimo renovacaoEmprestimo (Long emprestimoId){
        //BUSCA O EMPRÉSTIMO PELO ID
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        // Regra 1: SABER SE O LIVRO FOI DEVOLVIDO
        if(!emprestimo.getAtivo()){
            throw new RuntimeException("Não é possível renovar um empréstimo devolvido.\n");
        }

        //Regra 2: SABER SE O LIVRO JÁ FOI RENOVADO 2 VEZES
        if(emprestimo.getContagemRenovacao() >= 2){
            throw new RuntimeException("Número máximo de renovações atingido");
        }

        emprestimo.setDataRetorno(emprestimo.getDataRetorno().plusDays(15)); //ADICIONAR MAIS 15 DIAS
        emprestimo.setContagemRenovacao(emprestimo.getContagemRenovacao() + 1); //INCREMENTAR A RENOVAÇÃO

        return emprestimoRepository.save(emprestimo);
    }

}
