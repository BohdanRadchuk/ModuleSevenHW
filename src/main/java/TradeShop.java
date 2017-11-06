import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeShop {
    private ArrayList<Fruit> fruitStorage;

    public ArrayList<Fruit> getFruitStorage() {
        return fruitStorage;
    }

    public void setNewFruit(Fruit fruit) {
        this.fruitStorage.add(fruit);
    }

    public TradeShop() {
        fruitStorage  = new ArrayList<>();
    }

    public void addFruits(String pathToJsonFile){

        try {
            String json = new Scanner(new File(pathToJsonFile)).useDelimiter("\\Z").next();
            
            Fruit fruitObject = JSON.parseObject(json, Fruit.class);

            fruitStorage.add(fruitObject);
            /*Scanner scanner = new Scanner(new File(pathToJsonFile));
            String json = scanner.toString();
            while (scanner.hasNextLine()){
                fru*/

            } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            }

        }

    public void save(String pathToJsonFile){
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
    public void show (){
        for (Object furit:fruitStorage
             ) {
            System.out.println(furit);
        }
    }

}
