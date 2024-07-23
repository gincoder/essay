package com.panther.test.net;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Client {
    
        public static void main(String[] argv) throws IOException, InterruptedException {
    
            List clients = new LinkedList<>();
    
            for(int i = 0; i < 1000000; i++) {
                Socket client = new Socket("127.0.0.1", 3001);
                clients.add(client);
            }
            Thread.sleep(10000000);
    
        }
    
    }