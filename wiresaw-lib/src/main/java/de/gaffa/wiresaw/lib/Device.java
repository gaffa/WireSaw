package de.gaffa.wiresaw.lib;

public class Device {

    private String name;
    private String hostAdress;
    private Integer port;

    public Device(String name, String hostAdress, Integer port) {
        this.name = name;
        this.hostAdress = hostAdress;
        this.port = port;
    }

    public String toString() {
        return "DEVICE: " + name + " has host adress " + hostAdress + " and listens on port: " + port;
    }
}
