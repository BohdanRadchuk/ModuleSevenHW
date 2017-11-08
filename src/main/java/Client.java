public class Client {
    private int count;
    private FruitType fruitType;
    private String name;

    public Client(int count, FruitType fruitType,String name ) {
        this.count = count;
        this.fruitType = fruitType;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FruitType getFruitType() {
        return fruitType;
    }

    public void setFruitType(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", fruitType=" + fruitType +
                ", count=" + count +
                '}';
    }
}
