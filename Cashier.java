public class Cashier extends ProductQueueConsumer {
  private float totalValue = 0;

  public Cashier(ProductQueue productQueue) {
    super(productQueue);
  }

  private void addProductValue(Product product) {
    totalValue += product.price;
  }

  @Override
  public void run() {
    while(!this.hasfinished) {
      Product product = this.getProduct();
      addProductValue(product);
      this.printAction(String.format("checking item %s", product.name));
    }
  }

  @Override
  protected void finish() {
    this.printAction(String.format("Total value is %f", totalValue));
  }

}
