package com.davidlima.library.repository.funcao;

import com.davidlima.library.domain.entity.funcao.Emprestimo;
import com.davidlima.library.domain.entity.objeto.Livro;
import com.davidlima.library.domain.entity.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    long countByUsuarioAndAtivoTrue(Usuario usuario); //QUANTOS EMPRÉSTIMOS ATIVOS O USUÁRIO TEM

    boolean existsByLivroAndAtivoTrue(Livro livro); //VERIFICA SE O IVRO JÁ ESTÁ EMPRÉSTADO

    List<Emprestimo> findByUsuarioAndAtivoTrue(Usuario usuario); //LISTA EMPRÉSTIMOS ATIVOS DO USUÁRIO

    Optional<Emprestimo> findByLivroAndAtivoTrue(Livro livro);
}
