package io.github.danielsbaumann.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado");
    }
}
