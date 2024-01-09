package Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceImp implements Service {

    private static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "tiger";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("CONNECTION UNSUCCESSFUL");
        }
    }

    @Override
    public void placeOrder(String cname, int id, int qty) {
        try {
            CallableStatement cstmt = conn.prepareCall("{call checkOutBooks(?, ?, ? )}");
            cstmt.setString(1, cname);
            cstmt.setInt(2, id);
            cstmt.setInt(3, qty);

            cstmt.execute();

            boolean status = cstmt.getBoolean(4);
            int productQty = cstmt.getInt(5);

            if (status) {
                System.out.println(productQty + " order place successfully!");
            } else {
                System.out.println("Not enough available items for checkout.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product uptProduct) {
        String updateQuery = "UPDATE PRODUCT_INFO SET product_name = ? , product_qty = ? , product_price = ? WHERE product_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(2 , uptProduct.getProductName());
            pstmt.setInt(3,uptProduct.getProductQty());
            pstmt.setDouble(4 , uptProduct.getProductPrice());
           pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("PRODUCT NOT FOUND !!");
        }
    }

    public void removeProduct(int productId) {
        String delQuery = "DELETE FROM product_info WHERE product_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(delQuery);
            pstmt.setInt(1 , productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("PRODUCT NOT FOUND !!");
        }
    }


    public List<Product> displayAllProducts()
    {
        String displayQuery = "SELECT * FROM PRODUCT_INFO";
        List<Product> productList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQuery);

            while (rs.next())
            {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int productQty =rs.getInt(3);
                double productPrice = rs.getDouble(4);
                Product product = new Product(productId , productName , productQty ,productPrice);
                productList.add(product);
            }
        } catch (SQLException e) {

        }
        return productList;
    }

    @Override
    public List<Order> displayAllOrders() {
        return null;
    }


}
