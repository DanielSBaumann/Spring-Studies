package io.github.danielsbaumann.rest.controller;

import io.github.danielsbaumann.domain.entity.Usuario;
import io.github.danielsbaumann.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario user) {
        String senhaCriptografada = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaCriptografada);
        System.out.println("Imprimindo senha apos criptografia -> " + senhaCriptografada);
        return usuarioService.salvar(user);
    }
}
