import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeShop {
    ArrayList<Fruit> fruitStorage = new ArrayList<>();

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

    }

        //JSON.to


}
