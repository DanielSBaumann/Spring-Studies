package io.github.danielsbaumann.security.jwt;

import io.github.danielsbaumann.SaleApplication;
import io.github.danielsbaumann.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        //claims são informacoes do token ######opcional
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("emailusuario", "usuario@gmail.com");
//        claims.put("roles", "admin");


        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                //.setClaims(claims)  //opcional
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public String obterUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext contexto = SpringApplication.run(SaleApplication.class);
        JWTService service = contexto.getBean(JWTService.class);

        Usuario usuario = Usuario
                .builder()
                .login("fulano")
                .build();

        String token = service.gerarToken(usuario);
        System.out.println("Imprimindo token -> ");
        System.out.println(token);

        boolean isTokenValid = service.tokenValido(token);
        System.out.println("Token valido ? " + isTokenValid);

        System.out.println(service.obterUsuario(token));
    }

}
