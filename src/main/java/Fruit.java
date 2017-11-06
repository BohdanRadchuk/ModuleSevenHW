import java.util.Date;

public class Fruit {
    private FruitType fruitType;
    private int expirationDays;
    private Date incomeDate ;
    private int price;

    public Fruit(FruitType fruitType, int expirationDays, Date incomeDate, int price) {
        this.fruitType = fruitType;
        this.expirationDays = expirationDays;
        this.incomeDate = incomeDate;
        this.price = price;
    }


    public FruitType getFruitType() {
        return fruitType;
    }

    public void setFruitType(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
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
                ", expirationDays=" + expirationDays +
                ", incomeDate=" + incomeDate +
                ", price=" + price +
                '}';
    }
}
