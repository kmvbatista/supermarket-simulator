import java.util.ArrayList;
import java.util.List;

public class Customer extends Thread {
  private List<Product> shoppingCart = new ArrayList<Product>();

  @Override
  public void run() {
    int numberOfitensToBuy = getNumberOfitensToBuy();
    for (int i = 0; i < numberOfitensToBuy; i++) {
      addProductToCart();
    }
    checkout();
  }

  private void checkout() {
    printCustomerAction("Going towards register");
    Register register = new Register();
    for (Product product : shoppingCart) {
      register.passProduct(product);
      printCustomerAction("registering product "+product.name);
    }
    double totalToPay = register.finishAndGetTotalValue();
    printCustomerAction(String.format("paying R$ %.2f", totalToPay));
  }

  private void spendTime() {
    try {
      Thread.sleep(Randomer.getRandomIntFromInterval(1000, 2000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void addProductToCart() {
    Product product = ProductsInStore.getRandomProduct();
    shoppingCart.add(product);
    printCustomerAction(String.format("putting item %s inside cart", product.name));
    spendTime();
  }

  private int getNumberOfitensToBuy() {
    int minimum = 10;
    int maximum = 30;
    return Randomer.getRandomIntFromInterval(minimum, maximum);
  }

  private void printCustomerAction(String action) {
    System.out.println(String.format("Customer: %s %s", 
      Timer.getCurrentTime(), action));
  }

}
