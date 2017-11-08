import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TradeShop shop = new TradeShop();

        Date date= new Date(2017, 10, 20);
        /*shop.loadClients("files/order1");                   //not working
        System.out.println(shop.clients);
        shop.save("files/myClient", shop.clients);*/
        Fruit fruit1 = new Fruit(FruitType.banana, 20, date, 20);
        Fruit fruit2 = new Fruit(FruitType.cherry, 21, date, 30);
        date = new Date(2017, 10, 23);
        Fruit fruit3 = new Fruit(FruitType.apple, 30, date, 25);
        Fruit fruit4 = new Fruit(FruitType.pineapple, 60, date, 80);
        Fruit fruit5 = new Fruit(FruitType.banana, 20, date, 20);
        shop.setNewFruit(fruit1);
        shop.setNewFruit(fruit2);
        shop.setNewFruit(fruit3);
        shop.setNewFruit(fruit4);
        shop.setNewFruit(fruit5);
        shop.addFruits("files/JSONtoLoad.json");
        shop.show(shop.getFruitStorage());

        Client client = new Client(1,FruitType.apple,"Вася");
        Client client1 = new Client(500,FruitType.apple,"Джон");
        Client client2 = new Client(2,FruitType.banana,"Джон");
        Clients clients = new Clients();

        clients.setClients(client);
        clients.setClients(client1);
        clients.setClients(client2);
        shop.save("files/myClient", clients);

        shop.sell(clients.getClients());
        System.out.println("Money = " + shop.getMoneyBalance());
        //shop.save("files/SavedJSON.json",shop.getFruitStorage());
        System.out.println("shop after selling:");
        shop.show(shop.getFruitStorage());
        System.out.println("Clients left" + clients.getClients());

        Date spoilDate = new Date(2017, 11, 25);
        System.out.println("-----------------------------------");
        System.out.println("Spoiled fruits to date" + spoilDate);
        shop.getSpoiledFruits(spoilDate);
        System.out.println("spoiled fruit type to date " + spoilDate);
        shop.getSpoiledFruits(spoilDate, FruitType.banana);
        System.out.println("-----------------------------------");
        System.out.println("fruits available to sell: ");
        shop.show(shop.getAvailableFruits(spoilDate));
        System.out.println("fruit type available to sell: ");
        shop.show(shop.getAvailableFruits(spoilDate, FruitType.apple));

        System.out.println("-----------------------------------");
        System.out.println("all fruits added at "+ date);
        shop.show(shop.getAddedFruits(date));
        System.out.println("Fruits type added at "+ date);
        shop.show(shop.getAddedFruits(date,FruitType.pineapple));
    }
}
