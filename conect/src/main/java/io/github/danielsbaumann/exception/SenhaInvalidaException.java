package io.github.danielsbaumann.exception;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha invalida.");
    }
}
