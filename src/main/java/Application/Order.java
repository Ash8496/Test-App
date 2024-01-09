package Application;

public class Order {

    private int orderId;
    private String customerName;
    private int productId;
    private int productQty;
    private double totalAmt;

    public Order() {
    }

    public Order(String customerName, int productId, int productQty) {
        this.customerName = customerName;
        this.productId = productId;
        this.productQty = productQty;
    }

    public Order(int orderId, String customerName, int productId, int productQty, double totalAmt) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productId = productId;
        this.productQty = productQty;
        this.totalAmt = totalAmt;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }
}
