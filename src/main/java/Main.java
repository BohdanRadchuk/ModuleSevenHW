import sun.util.calendar.CalendarDate;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TradeShop shop = new TradeShop();

        Date date= new Date(2017, 10, 20);
        //shop.addFruits("files/JSONtoLoad.json");


        Fruit fruit1 = new Fruit(FruitType.banana, 20, date, 20);
        Fruit fruit2 = new Fruit(FruitType.orange, 21, date, 30);
        date = new Date(2017, 10, 23);
        Fruit fruit3 = new Fruit(FruitType.apple, 30, date, 25);
        Fruit fruit4 = new Fruit(FruitType.pineapple, 60, date, 80);
        shop.setNewFruit(fruit1);
        shop.setNewFruit(fruit2);
        shop.setNewFruit(fruit3);
        shop.setNewFruit(fruit4);
        shop.save("files/SavedJSON.json");
       // shop.show(shop.getFruitStorage());

        Date spoilDate = new Date(2017, 11, 25);
        System.out.println("Spoiled fruits to date" + spoilDate);
        shop.getSpoiledFruits(spoilDate);
        System.out.println("spoiled fruit type to date " + spoilDate);
        shop.getSpoiledFruits(spoilDate, FruitType.banana);
        System.out.println("fruits available to sell: ");
        shop.show(shop.getAvailableFruits(spoilDate));
        System.out.println("fruit type available to sell: ");
        shop.show(shop.getAvailableFruits(spoilDate, FruitType.apple));


    }
}
