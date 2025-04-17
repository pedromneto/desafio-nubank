package com.nubank.clientmanager.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    private static String MENSAGEM_PADRAO = "Cliente com ID %d não encontrado";
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public ClienteNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
    public ClienteNaoEncontradoException(Throwable cause) {
        super(cause);
    }
    public ClienteNaoEncontradoException(long id) {
        super(String.format(MENSAGEM_PADRAO, id));
    }
}
