package org.chatbot.exception;

public class ChatClientException extends Exception {
    public ChatClientException(String message) {
        super(message);
    }

    public ChatClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
