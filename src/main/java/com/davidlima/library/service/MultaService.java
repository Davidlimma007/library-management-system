package com.davidlima.library.service;

import com.davidlima.library.domain.entity.funcao.Multa;
import com.davidlima.library.repository.funcao.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        multa.setCriadoEm(LocalDate.now());

        return multaRepository.save(multa);
    }
}
