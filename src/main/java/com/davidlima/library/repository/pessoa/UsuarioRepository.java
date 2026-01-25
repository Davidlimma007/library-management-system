package com.davidlima.library.repository.pessoa;

import com.davidlima.library.domain.entity.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
}
