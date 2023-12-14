package sample.Chat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class Networking {

    private connectionThread connThread = new connectionThread();
    private Consumer<Serializable> onReceiveCallback;

    public Networking(Consumer<Serializable> onReceiveCallback) {
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }

    public void startConnection() throws Exception {
        connThread.start();
    }

    public void Send(Serializable data) throws Exception {
        connThread.Outpt.writeObject(data);
    }

    protected abstract boolean isServer();

    protected abstract String getIP();

    protected abstract int getPort();

    private class connectionThread extends Thread {

        private Socket socket;
        private ObjectOutputStream Outpt;

        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                 ObjectOutputStream Output = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream Input = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.Outpt = Output;
                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) Input.readObject();
                    onReceiveCallback.accept(data);
                }
            } catch (Exception e) {
                onReceiveCallback.accept("Connection Closed");
            }
        }
    }
    public void closeConnection() throws Exception {
        connThread.socket.close();
    }
}
