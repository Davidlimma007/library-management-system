package com.davidlima.library.repository.pessoa;

import com.davidlima.library.domain.entity.pessoa.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
}
