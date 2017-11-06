import com.alibaba.fastjson.JSON;

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
    private int wallet;

    public TradeShop() {
        fruitStorage = new ArrayList<>();
        wallet = 0;
    }

    public ArrayList<Fruit> getFruitStorage() {
        return fruitStorage;
    }

    public void setNewFruit(Fruit fruit) {
        this.fruitStorage.add(fruit);
    }

    public void addFruits(String pathToJsonFile) {
        this.fruitStorage = loadFromJson(pathToJsonFile);
    }

    public void save(String pathToJsonFile) {
        try {
            FileWriter writer = new FileWriter(pathToJsonFile);
            String json = JSON.toJSONString(fruitStorage);
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

        LocalDateTime ldate = LocalDateTime.from(date1.toInstant().atZone(ZoneId.systemDefault())).plusDays(plusDays);
        date1 = Date.from(ldate.atZone(ZoneId.systemDefault()).toInstant());
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

    public void sell(String pathToJsonFile){
        ArrayList<Fruit>order = loadFromJson(pathToJsonFile);
    }

    public ArrayList<Fruit> loadFromJson(String pathToJsonFile){
        ArrayList<Fruit> loadedFruits;
        try {
            String json = new Scanner(new File(pathToJsonFile)).useDelimiter("\\Z").next();
            loadedFruits= new ArrayList<>(JSON.parseArray(json, Fruit.class));
            return loadedFruits;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void show(ArrayList<Fruit> fruits) {
        for (Object furit : fruits
                ) {
            System.out.println(furit);
        }
    }
}
