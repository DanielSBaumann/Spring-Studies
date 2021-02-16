package io.github.danielsbaumann.lambda;

import java.util.function.Function;

class Usuario {

    private String nome;
    private int pontos;
    private boolean moderador;

    public Usuario(String nome, int pontos) {
        this.pontos = pontos;
        this.nome = nome;
        this.moderador = false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome=" + nome +
                ", pontos='" + pontos + '\'' +
                ", moderador='" + moderador + '\'' +
                '}';
    }

    public void tornarModerador() {

        this.moderador = true;
    }

    public boolean isModerador() {
        return moderador;
    }

    public void setModerador(boolean moderador) {
        this.moderador = moderador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
