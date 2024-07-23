package com.panther.test.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    public static void main(String[] argv) throws IOException {

        ServerSocket serverSocket = new ServerSocket();

        InetSocketAddress addr = new InetSocketAddress(3001);

        serverSocket.bind(addr);

        List list = new LinkedList<>();
        while (true) {
            Socket accept = serverSocket.accept();
            list.add(accept);
            System.out.println(list.size());
        }
    }
}