package com.davidlima.library.service;

import com.davidlima.library.domain.entity.pessoa.Usuario;
import com.davidlima.library.repository.pessoa.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public void cadastrarUsuario (Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario listarUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email digitado (" + email + ") não corresponde a nenhum usuário."));
    }

    public List<Usuario> listarTodosOsUsuarios (){
       return usuarioRepository.findAll();
    }

    public void atualizarUsuarioPorEmail(String email, Usuario usuario){
        Usuario usuarioExiste = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrato"));

        //SE O PARÂMETRO QUE FOR PASSADO FOR NULO, ELE PEGA O PARÂMETRO EXISTENTE E DEIXA COMO ESTA
        Usuario usuarioAtualizado = Usuario.builder()
                .id(usuarioExiste.getId())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioExiste.getNome())
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioExiste.getEmail())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : usuarioExiste.getSenha())
                .papel(usuarioExiste.getPapel())
                .build();

        usuarioRepository.save(usuarioAtualizado);
    }

    public void deletarUsuario (String email){
        usuarioRepository.deleteByEmail(email);
    }
}
