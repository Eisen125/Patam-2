package ServerHandler;

public class ServerHandler {
    private int port;
    private volatile boolean stop;
    private ClientHandler handler;

    public MySerialServer(int port, ClientHandler handler) {
        this.port = port;
        this.handler = handler;
        stop = false;
    }

    public void start() {
        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void open(int port, ClientHandler c) {
    }

    @Override
    public void stop() {
        stop = true;
    }

    // private methods
    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(3000);
        Socket client = null;
        while (!stop) {
            try {
                client = server.accept();
                try {
                    handler.handleClient(client.getInputStream(), client.getOutputStream());
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        client.close();
        server.close();
    }
}
