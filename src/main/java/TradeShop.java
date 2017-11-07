import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TradeShop {
    private ArrayList<Fruit> fruitStorage;
    private int moneyBalance;
    public Clients clients;



    public TradeShop() {
        this.fruitStorage = new ArrayList<>();

        this.moneyBalance = 0;

    }

    public ArrayList<Fruit> getFruitStorage() {
        return fruitStorage;
    }

    public void setNewFruit(Fruit fruit) {
        this.fruitStorage.add(fruit);
    }



    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance( int price) {
        this.moneyBalance += price;
    }

    public void addFruits(String pathToJsonFile) {

        this.fruitStorage = new ArrayList<>(JSON.parseArray(loadFromJson(pathToJsonFile), Fruit.class));
    }

    public void loadClients (String pathToJsonFile) {
        JSONArray jsonMainArr = loadFromJson(pathToJsonFile).getJSONArray(pathToJsonFile);
       this.clients = JSON.parseObject (loadFromJson(pathToJsonFile), Clients.class);
    }

    public void save(String pathToJsonFile, Object object) {
        try {
            FileWriter writer = new FileWriter(pathToJsonFile);
            String json = JSON.toJSONString(object);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Fruit> getSpoiledFruits(Date date) {
        ArrayList<Fruit> spoiledFruitsToDate = new ArrayList<>();
        for (int i = 0; i < fruitStorage.size(); i++) {
            Date date1 = plusDays(fruitStorage.get(i).getIncomeDate(), fruitStorage.get(i).getExpirationDays());
            if (date1.getTime() < (date.getTime())) {
                spoiledFruitsToDate.add(fruitStorage.get(i));
            }
        }
        show(spoiledFruitsToDate);
        return spoiledFruitsToDate;
    }

    public ArrayList<Fruit> getSpoiledFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> spoiledFruitsToDate = new ArrayList<>();
        for (int i = 0; i < fruitStorage.size(); i++) {
            if (fruitStorage.get(i).getFruitType() == fruitType) {
                Date date1 = plusDays(fruitStorage.get(i).getIncomeDate(), fruitStorage.get(i).getExpirationDays());
                if (date1.getTime() < (date.getTime())) {
                    spoiledFruitsToDate.add(fruitStorage.get(i));
                }
            }
        }
        show(spoiledFruitsToDate);
        return spoiledFruitsToDate;
    }

    public ArrayList<Fruit> getAvailableFruits(Date date) {
        ArrayList<Fruit> availableFruits = new ArrayList<>();
        for (int i = 0; i < fruitStorage.size(); i++) {
            Date date1 = plusDays(fruitStorage.get(i).getIncomeDate(), fruitStorage.get(i).getExpirationDays());
            if (date1.compareTo(date) >= 0) {
                availableFruits.add(fruitStorage.get(i));
            }
        }
        if (availableFruits.isEmpty())
            System.out.println("there is no available fruits to sell");
        return availableFruits;
    }

    public ArrayList<Fruit> getAvailableFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> availableFruits = new ArrayList<>();
        for (int i = 0; i < fruitStorage.size(); i++) {
            if (fruitStorage.get(i).getFruitType() == fruitType) {
                Date date1 = plusDays(fruitStorage.get(i).getIncomeDate(), fruitStorage.get(i).getExpirationDays());
                if (date1.compareTo(date) >= 0) {
                    availableFruits.add(fruitStorage.get(i));
                }
            }
        }
        if (availableFruits.isEmpty())
            System.out.println("there is no available fruits to sell");
        return availableFruits;
    }

    public Date plusDays(Date startDate, int plusDays) {
        Date date1 = startDate;

        LocalDateTime ldt = LocalDateTime.from(date1.toInstant().atZone(ZoneId.systemDefault())).plusDays(plusDays);
        date1 = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }

    public ArrayList<Fruit> getAddedFruits(Date date) {
        ArrayList<Fruit> addedAtDay = new ArrayList();
        for (int i = 0; i < fruitStorage.size(); i++) {
            if (fruitStorage.get(i).getIncomeDate().compareTo(date) == 0) {
                addedAtDay.add(fruitStorage.get(i));
            }
        }
        return addedAtDay;
    }

    public ArrayList<Fruit> getAddedFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> addedAtDay = new ArrayList();
        for (int i = 0; i < fruitStorage.size(); i++) {
            if (fruitStorage.get(i).getFruitType() == fruitType) {
                if (fruitStorage.get(i).getIncomeDate().compareTo(date) == 0) {
                    addedAtDay.add(fruitStorage.get(i));
                }
            }
        }
        return addedAtDay;
    }

  /*  public void sell(String pathToJsonFile) {
        ArrayList<Fruit> order = loadFromJson(pathToJsonFile);
        ArrayList<Fruit> check = new ArrayList<>();
        ArrayList<Integer> fruitStorageMatchIndex = new ArrayList<>();
        for (Fruit orderfruit : order
                ) {
            for (int i = 0; i < fruitStorage.size(); i++) {
                if (orderfruit.equals(fruitStorage.get(i))) {
                    check.add(fruitStorage.get(i));
                    fruitStorageMatchIndex.add(i);
                }
            }
        }
        if (order.equals(check)){
            int count=0;
            for(int i = 0; i<fruitStorageMatchIndex.size();i++){
                for (int j = 0; j<order.size();j++)
                if (fruitStorage.get(fruitStorageMatchIndex.get(i))==order.get(j)){
                    setMoneyBalance(fruitStorage.get(fruitStorageMatchIndex.get(i)).getPrice());
                    fruitStorage.remove(fruitStorageMatchIndex.get(i-count));
                    count++;
                }
            }

        }
    }*/

    public String loadFromJson( String pathToJsonFile){

        try {
            String json = new Scanner(new File(pathToJsonFile)).useDelimiter("\\Z").next();

            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void show(ArrayList fruits) {
        for (Object fruit : fruits
                ) {
            System.out.println(fruit);
        }
    }

    @Override
    public String toString() {
        return "TradeShop{" +
                "moneyBalance=" + moneyBalance +
                ", clients=" + clients +
                '}';
    }
}
