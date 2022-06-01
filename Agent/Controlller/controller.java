package Controlller;
import Model.model;

import java.io.FileReader;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class controller implements Observer {
private model model;
public Socket socket;

    public Model.model getModel() {
        return model;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    //we need a function to get our data from the backend
    public void Instructions(FileReader Fr)
    {

    }





    @Override
    public void update(Observable o, Object arg) {

    }
}
