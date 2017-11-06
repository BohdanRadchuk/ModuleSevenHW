import java.util.Date;

public class Fruit {
    private FruitType fruitType;
    private int expirationDate;
    private Date incomeDate ;
    private int price;

    public Fruit(FruitType fruitType, int expirationDate, Date incomeDate, int price) {
        this.fruitType = fruitType;
        this.expirationDate = expirationDate;
        this.incomeDate = incomeDate;
        this.price = price;
    }


    public FruitType getFruitType() {
        return fruitType;
    }

    public void setFruitType(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "fruitType=" + fruitType +
                ", expirationDate=" + expirationDate +
                ", incomeDate=" + incomeDate +
                ", price=" + price +
                '}';
    }
}
