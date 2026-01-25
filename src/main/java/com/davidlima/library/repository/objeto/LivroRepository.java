package com.davidlima.library.repository.objeto;

import com.davidlima.library.domain.entity.objeto.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Long> {
}
