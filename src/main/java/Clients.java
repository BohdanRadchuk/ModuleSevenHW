import java.util.ArrayList;

public class Clients<T> {


    private ArrayList<T> clients = new ArrayList<>();


    public Clients(ArrayList<T> arrayList) {
        this.clients = arrayList;
    }



    public ArrayList<T> getClients() {
        return clients;
    }

    public void setClients(ArrayList<T> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + clients +
                '}';
    }
}
