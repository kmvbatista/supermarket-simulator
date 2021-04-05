public class Register {

  private ProductQueue productQueue;
  private Cashier cashier;
  private Bagger bagger;

  public Register() {
    this.productQueue = new ProductQueue();
    ProductQueue baggingTable = new ProductQueue();
    this.cashier = new Cashier(productQueue, baggingTable);
    this.bagger = new Bagger(baggingTable);
    start();
  }

  private void start() {
    this.bagger.start();
    this.cashier.start();
  }

  public void passProduct(Product product) {
    productQueue.setItem(product);
  }

  public float finishAndGetTotalValue() {
    cashier.notifyCustomerFinished();
    try {
      cashier.join();
      bagger.notifyCashierFinished();
      bagger.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return cashier.getTotalValue();
  }
}
