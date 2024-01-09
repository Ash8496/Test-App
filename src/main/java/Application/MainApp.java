package Application;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Service service = new ServiceImp();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("1. DISPLAY ALL PRODUCTS");
        System.out.println("2. REMOVE PRODUCT");
        System.out.println("3. UPDATE PRODUCT");
        System.out.println("4. PLACE ORDER");
        System.out.println("5. DISPLAY ALL ORDER");
        System.out.println("6. EXIT");
        int ch = sc.nextInt();

        switch (ch){
            case 1:
                displayAllProduct();
            break;
            case 2:
                removeProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4 :
                placeOrder();
                break;
            case 5:
                displayAllOrders();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("INVALID INPUT");
        }
        main(args);

    }

    private static void displayAllProduct() {
        List<Product> ProductList= service.displayAllProducts();
        System.out.println("\n=================================================");
        System.out.println("ID\t \t Product Name\t ProductQty \t Product Price");
        for (Product p1 : ProductList) {
            System.out.println(p1.getProductId() + "\t\t" + p1.getProductName() + "\t\t" + p1.getProductQty()+ "\t\t" +p1.getProductPrice());
        }
    }

    public static void updateProduct()
    {
        System.out.println("ENTER ID ");
        int productId = sc.nextInt();
        System.out.println("ENTER UPDATED PRODUCT NAME ");
        String productName = sc.next() ;
        System.out.println("ENTER UPDATED PRODUCT QTY ");
        int productQty = sc.nextInt();
        System.out.println("ENTER UPDATED PRODUCT PRICE ");
        double productPrice = sc.nextDouble();

        Product uptProduct = new Product(productId,productName ,productQty, productPrice);
        service.updateProduct(uptProduct);
        System.out.println(" PRODUCT UPDATED !!");
    }

    private static void placeOrder() {
        System.out.println("==PLACE ORDER==");
        System.out.println("ENTER YOUR NAME:");
        String cname = sc.nextLine();
        System.out.println("ENTER THE PRODUCT ID :");
        int id = sc.nextInt();
        System.out.println("ENTER THE QTY");
        int qty = sc.nextInt();
        service.placeOrder(cname, id,qty);
    }

    private static void removeProduct(){
        System.out.print("Enter Product ID: ");
        int  productId= sc.nextInt();
        sc.nextLine();
        service.removeProduct(productId);
    }

    private static void displayAllOrders() {
        List<Order> OrderList = service.displayAllOrders();
        System.out.println("\n=================================================");
        System.out.println("ID\t \t Customer Name\t ProductId \t ProductQty \t Total Amount");
        for (Order o1 : OrderList) {
            System.out.println(o1.getOrderId() + "\t\t" + o1.getCustomerName() + "\t\t\t" + o1.getProductId() + "\t\t" + o1.getProductQty()+ "\t\t" +o1.getTotalAmt());
        }
    }

}

