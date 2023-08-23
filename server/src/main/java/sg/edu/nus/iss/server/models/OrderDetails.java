package sg.edu.nus.iss.server.models;

public class OrderDetails {

    private String itemName;
    private int quantity;
    private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(String itemName, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails [itemName=" + itemName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
    }

}
