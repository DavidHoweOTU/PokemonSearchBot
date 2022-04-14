package com.example.chatbot;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private final int   port          = 6666;
    public ServerSocket ss            = null;
    private boolean     closed        = false;
    protected Thread    runningThread = null;

    @Override
    public void run() {
        synchronized (this) { this.runningThread = Thread.currentThread(); }

        // open socket
        try { ss = new ServerSocket(port); }
        catch (IOException e) { System.out.println("ERROR: Could not open current port"); }

        // run server
        while (!closed) {
            // listen for client
            Socket s = null;
            try { s = ss.accept(); }
            catch (IOException e) {
                if (closed) {return;}
                System.out.println("ERROR: Could not accept client connection");
            }

            // TODO - process requests with new runnable
//            new Thread(...)
        }
    }

    public void endCommunications() {
        closed = true;

        // close socket
        try { ss.close(); }
        catch (IOException e) { e.printStackTrace(); }

        // close app
        Platform.exit();
    }
}
