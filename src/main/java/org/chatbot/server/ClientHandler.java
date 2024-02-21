package org.chatbot.server;

import org.chatbot.database.DatabaseConnection;
import org.chatbot.logic.ChatbotLogic;
import org.chatbot.response.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ChatbotLogic chatbotLogic;

    public ClientHandler(Socket socket) throws SQLException {
        this.clientSocket = socket;
        this.chatbotLogic = new ChatbotLogic(new DatabaseConnection("jdbc:mysql://localhost/chatbot_db", "chatbot-app", "haslo"));
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            //  Wysyłanie wiadomości powitalnej od razu po nawiązaniu połączenia
            Response greeting = chatbotLogic.processInput("");
            out.println(greeting.message());
            out.flush();

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Prepare a response based on the input
                Response response = chatbotLogic.processInput(inputLine);
                // Send the response to the client
                System.out.println("Sending response: " + response.message());
                out.println(response.message());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatbotLogic.exit();
        }
    }
}
