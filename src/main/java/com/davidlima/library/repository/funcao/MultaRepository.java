package com.davidlima.library.repository.funcao;

import com.davidlima.library.domain.entity.funcao.Multa;
import com.davidlima.library.domain.entity.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Long> {

    boolean existsByUsuarioAndPagaFalse(Usuario usuario);
}
