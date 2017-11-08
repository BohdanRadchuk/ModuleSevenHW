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
    private int moneyBalance;

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

    public void setMoneyBalance(int price) {
        this.moneyBalance += price;
    }

    public void addFruits(String pathToJsonFile) {                                               // добавление поставки из файла
        this.fruitStorage.addAll(JSON.parseArray(loadFromJson(pathToJsonFile), Fruit.class));
        //this.fruitStorage = new ArrayList<>(JSON.parseArray(loadFromJson(pathToJsonFile), Fruit.class));                     //перезаписывание магазина
    }

    public Clients loadClients(String pathToJsonFile) {                                 //not working

        return JSON.parseObject(loadFromJson(pathToJsonFile), Clients.class);
    }

    public void save(String pathToJsonFile, Object object) {                     //сохранение объекта в JSON формате в файл
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

    public ArrayList<Fruit> getSpoiledFruits(Date date) {                     //получаем массив испорченх фруктов в к дате
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

    public ArrayList<Fruit> getSpoiledFruits(Date date, FruitType fruitType) {                     //получаем массив испорченых фруктов типа "Н" к дате
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

    public ArrayList<Fruit> getAvailableFruits(Date date) {                     //проверка на не испорченые фрукты к дате
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

    public ArrayList<Fruit> getAvailableFruits(Date date, FruitType fruitType) {                     // перегруженая проверка на не испорченые фрукты по типу к дате
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

    public Date plusDays(Date startDate, int plusDays) {                     //добавление дней к дате
        Date date1 = startDate;

        LocalDateTime ldt = LocalDateTime.from(date1.toInstant().atZone(ZoneId.systemDefault())).plusDays(plusDays);
        date1 = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }

    public ArrayList<Fruit> getAddedFruits(Date date) {                     //проверка на поставку в "день"
        ArrayList<Fruit> addedAtDay = new ArrayList();
        for (int i = 0; i < fruitStorage.size(); i++) {
            if (fruitStorage.get(i).getIncomeDate().compareTo(date) == 0) {
                addedAtDay.add(fruitStorage.get(i));
            }
        }
        return addedAtDay;
    }

    public ArrayList<Fruit> getAddedFruits(Date date, FruitType fruitType) {    //перегруженая проверка на поставку в "день" по типу фрукта
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

    public void sell(ArrayList<Client> clients) {                           //selling according clients orders


        for (int i = 0; i < clients.size(); i++) {                           //проходим по всем клентам
            ArrayList<Integer> fruitStorageMatchIndex = new ArrayList<>();
            int count = 0;
            int clientCount = clients.get(i).getCount();
            for (int j = 0; j < fruitStorage.size(); j++) {

                if (clientCount > count) {                                  //если нашло столько типов фруктов на складе как в заказе - больше не ищем
                    if (clients.get(i).getFruitType() == fruitStorage.get(j).getFruitType()) {          //сравниваем тип фрука в заказе с массивом фруктов
                        count++;                                        //добавляем в счётчик фрукт если находим
                        fruitStorageMatchIndex.add(j);                  //записываем индекс подходящего фрукта
                    }
                }
            }

            if (count == clientCount) {                                     //если нашли достаточно фруктов на складе - продаем

                for (int k = fruitStorageMatchIndex.size() - 1; k >= 0; k--) {
                    int index = fruitStorageMatchIndex.get(k);
                    setMoneyBalance(fruitStorage.get(index).getPrice());        //пополняем кошелёк
                    this.fruitStorage.remove(index);                            //удаляем фрукт под индексом который записывали в массив индексов
                    clientCount--;                                              //уменьшаем заказ на 1
                }
                clients.remove(i);                                              //удаляем довольного клиента
            }
        }
    }

    public String loadFromJson(String pathToJsonFile) {

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

                '}';
    }
}
