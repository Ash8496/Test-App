package Application;

import java.util.List;

public interface Service {


    void removeProduct(int productId);

    List<Order> displayAllOrders();

    List<Product> displayAllProducts();
    

    boolean placeOrder(Order order);

    int updateProduct(Product uptProduct);
}
