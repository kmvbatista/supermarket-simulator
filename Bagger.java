public class Bagger extends ProductQueueConsumer{
  
  protected Bagger(ProductQueue productQueue) {
    super(productQueue);
  }

  @Override
  public void run() {
    while(!this.hasfinished || this.hasItemToGet()) {
      Product product = this.getProduct();
      spendTimeInSeconds(2);
      this.printAction(String.format("bagging item %s", product.name));
    }
    finish();
  }

  @Override
  protected void finish() {
    this.printAction("finishing bagging");
  }

  public void notifyCashierFinished() {
    this.hasfinished = true;
  }
}
