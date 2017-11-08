import java.util.ArrayList;

public class Clients {

    private ArrayList<Client> clients = new ArrayList<>();

    public Clients() {
    }

    /*public Clients(ArrayList<Client> client) {
        this.clients = client;
    }*/
    public FruitType getFruitType(int i) {
        return clients.get(i).getFruitType();
    }

    public int getCount(int i) {
        return clients.get(i).getCount();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void addClients(Client client) {
        this.clients.add(client);
    }


    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + clients +
                '}';
    }
}
