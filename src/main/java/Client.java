public class Client {
    private String name;
    private FruitType fruitType;
    private int count;

    public Client(String name, FruitType fruitType, int count) {
        this.name = name;
        this.fruitType = fruitType;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FruitType getFruitType() {
        return fruitType;
    }

    public void setFruitType(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
