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
    public boolean placeOrder(Order order) {
        String query="{call placeOrder(?,?,?)}";
        try {
            CallableStatement cstmt=conn.prepareCall(query);
            cstmt.setString(1,order.getCustomerName());
            cstmt.setInt(2,order.getProductId());
            cstmt.setInt(3,order.getProductQty());
            cstmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
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
        List<Order> orderList=new ArrayList<>();
        String query="select * from order_info";
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs= stmt.executeQuery(query);

            while(rs.next()){
                int oId= rs.getInt(1);
                String cName= rs.getString(2);
                int pId= rs.getInt(3);
                int pQty= rs.getInt(4);
                double total= rs.getDouble(5);

                Order order=new Order(oId,cName,pId,pQty,total);

                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }


}
