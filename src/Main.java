import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            Server server = new Server("localhost", 8000);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
