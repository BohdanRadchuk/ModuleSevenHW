import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeShop {
    ArrayList<Fruit> fruitStorage = new ArrayList<>();

    public void addFruits(String pathToJsonFile){

        try {
            Scanner scanner = new Scanner(new File(pathToJsonFile));
            String json = scanner.toString();
            while (scanner.hasNextLine()){
                fruitStorage=  new Fruit(JSON.parse(json))

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //JSON.to

    }
}
