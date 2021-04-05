public class Bagger extends ProductQueueConsumer{
  
  protected Bagger(ProductQueue productQueue) {
    super(productQueue);
  }

  @Override
  public void run() {
    while(!this.hasfinished) {
      Product product = this.getProduct();
      this.printAction(String.format("bagging item", product.name));
    }
  }

  @Override
  protected void finish() {
    this.printAction("finishing bagging");
  }
  
}
