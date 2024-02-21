package org.chatbot.response;

public record Response(ResponseType type, String message) {

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
