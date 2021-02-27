package io.github.danielsbaumann.rest.controller;

import io.github.danielsbaumann.domain.entity.Usuario;
import io.github.danielsbaumann.exception.SenhaInvalidaException;
import io.github.danielsbaumann.rest.dto.CredenciaisDTO;
import io.github.danielsbaumann.rest.dto.TokenDTO;
import io.github.danielsbaumann.security.jwt.JWTService;
import io.github.danielsbaumann.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario user) {
        String senhaCriptografada = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaCriptografada);
        System.out.println("Imprimindo senha apos criptografia -> " + senhaCriptografada);
        return usuarioService.salvar(user);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            System.out.println("Entrou no try");
            Usuario usuario = Usuario
                    .builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            System.out.println("Teste usuario -> " + usuario.getLogin());
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            System.out.println("Esta imprimindo token ? " + token);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            System.out.println("Entrou no catch");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
