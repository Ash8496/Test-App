package Application;

import java.util.List;

public interface Service {


    void removeProduct(int productId);

    List<Order> displayAllOrders();

    List<Product> displayAllProducts();



    void updateProduct(Product uptProduct);

    boolean placeOrder(Order order);
}
