public class Cashier extends ProductQueueConsumer {
  private float totalValue = 0;
  private ProductQueue baggingTable;

  public float getTotalValue() {
    return totalValue;
  }

  public Cashier(ProductQueue productQueue, ProductQueue baggingTable) {
    super(productQueue);
    this.baggingTable = baggingTable;
  }

  private void addProductValue(Product product) {
    totalValue += product.price;
  }

  @Override
  public void run() {
    while(!this.hasfinished || this.hasItemToGet()) {
      Product product = this.getProduct();
      spendTime(2, 4); 
      addProductValue(product);
      this.printAction(String.format("checking item %s", product.name));
      baggingTable.setItem(product);
    }
    finish();
  }

  @Override
  protected void finish() {
    this.printAction(String.format("Total value is %.2f", totalValue));
  }

  public void notifyCustomerFinished() {
    this.hasfinished = true;
  }
}
