package com.davidlima.library.service;

import com.davidlima.library.domain.entity.funcao.Multa;
import com.davidlima.library.domain.entity.pessoa.Usuario;
import com.davidlima.library.repository.funcao.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;

    public Multa pagarMulta (Long multaId){

        Multa multa = multaRepository.findById(multaId)
                .orElseThrow(() -> new RuntimeException("Multa não encontrada."));

        if(multa.getPaga()){
            throw new RuntimeException("Esta multa já foi paga");
        }

        multa.setPaga(true);
        multa.setPagaEm(LocalDate.now());

        return multaRepository.save(multa);
    }

    public List<Multa> listarMultasPendentes (Usuario usuario){
        return multaRepository.findByUsuarioAndPagaFalse(usuario);
    }
}
