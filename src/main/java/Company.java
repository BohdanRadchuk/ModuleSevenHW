import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;

public class Company {
    private int moneyBalance;
    private ArrayList<TradeShop> company;
    private TradeShop tradeShop = new TradeShop();

    public Company() {
        this.moneyBalance = 0;
        this.company = new ArrayList<>();
    }

    public void save(String pathToJsonFile) {
        this.tradeShop.save(pathToJsonFile, company);
    }

    public void load(String pathToJsonFile) {
        this.company = new ArrayList<>(JSON.parseArray(tradeShop.loadFromJson(pathToJsonFile), TradeShop.class));
    }

    public void loadAdd(String pathToJsonFile) {
        this.company.addAll(JSON.parseArray(tradeShop.loadFromJson(pathToJsonFile), TradeShop.class));
    }

    public TradeShop getTradeShop(int i) {
        return this.company.get(i);
    }

    public int getCompanyBalance() {
        int summOfMoney = 0;
        for (TradeShop trades : company
                ) {
            summOfMoney += trades.getMoneyBalance();
        }
        return summOfMoney;
    }

    public ArrayList<Fruit> getSpoiledFruits(Date date) {
        ArrayList<Fruit> allSpoiledFruitsArr = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allSpoiledFruitsArr.addAll(trades.getSpoiledFruits(date));
        }
        return allSpoiledFruitsArr;
    }

    public ArrayList<Fruit> getSpoiledFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> allSpoiledFruitsOfTypeArr = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allSpoiledFruitsOfTypeArr.addAll(trades.getSpoiledFruits(date, fruitType));
        }
        return allSpoiledFruitsOfTypeArr;
    }

    public ArrayList<Fruit> getAvailableFruits(Date date) {
        ArrayList<Fruit> allAvailableFruits = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allAvailableFruits.addAll(trades.getAvailableFruits(date));
        }
        return allAvailableFruits;
    }

    public ArrayList<Fruit> getAvailableFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> allAvailableFruitsOfType = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allAvailableFruitsOfType.addAll(trades.getAvailableFruits(date, fruitType));
        }
        return allAvailableFruitsOfType;
    }

    public ArrayList<Fruit> getAddedFruits(Date date) {
        ArrayList<Fruit> allAdedFruits = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allAdedFruits.addAll(trades.getAddedFruits(date));
        }
        return allAdedFruits;
    }

    public ArrayList<Fruit> getAddedFruits(Date date, FruitType fruitType) {
        ArrayList<Fruit> allAdedFruitsType = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allAdedFruitsType.addAll(trades.getAddedFruits(date, fruitType));
        }
        return allAdedFruitsType;
    }

    public void addTradeShop(TradeShop tradeShop) {
        this.company.add(tradeShop);
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public ArrayList<Fruit> getCompany() {
        ArrayList<Fruit> allCompanyFruits = new ArrayList<>();
        for (TradeShop trades : company
                ) {
            allCompanyFruits.addAll(trades.getFruitStorage());
        }
        return allCompanyFruits;
    }

    public void add(ArrayList<TradeShop> company) {
        this.company = company;
    }
}
