package org.chatbot.logic;

public class CommandConstants {

    // SONARLINT: Add a private constructor to hide the implicit public one.
    private CommandConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESERVE = "Rezerwuj";
    public static final String SHOW_RESERVATIONS = "Pokaż rezerwacje";
    public static final String DELETE_RESERVATION = "Usuń rezerwację";
    public static final String CONFIRM = "tak";
    public static final String DENY = "nie";
}
