package org.chatbot.client;

import org.chatbot.exception.ChatClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final BufferedReader userInputReader;

    public ChatClient(String address, int port) throws ChatClientException {
        try {
            socket = new Socket(address, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            userInputReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            throw new ChatClientException("Error occurred while initializing chat client", e);
        }
    }
    public void send(String message) { out.println(message); }

    public String receive() throws ChatClientException {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new ChatClientException("Error occurred while receiving data", e);
        }
    }
    public void close() throws ChatClientException {
        try {
            in.close();
            out.close();
            socket.close();
            userInputReader.close();
        } catch (IOException e) {
            throw new ChatClientException("Error occurred while closing resources", e);
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient("localhost", 1234);

        // Print welcome message from chatbot
        System.out.println(client.receive());
        while (true) {
            // Get user input
            String userInput = client.userInputReader.readLine();
            // Send user input to server
            client.send(userInput);
            // Receive response from server
            System.out.println("Chatbot says: " + client.receive());
            if (userInput.equalsIgnoreCase("exit")) {
                client.close();
                break;
            }
        }
    }
}
