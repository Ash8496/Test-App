package Application;

import java.util.List;

public interface Service {
    void placeOrder(String cname, int id, int qty);

    void removeProduct(int productId);

    List<Order> displayAllOrders();

    List<Product> displayAllProducts();

    void updateProduct(Product uptProduct);
}
