public abstract class ProductQueueConsumer implements Runnable
{
  private ProductQueue productQueue;
  protected boolean hasfinished;

  protected abstract void finish();

  public void notifyFinishing() {
    this.hasfinished = true;
  }

  protected void printAction(String action) {
    System.out.println(String.format("%s: %s %s", this.getClass().getName(),
       Timer.getCurrentTime(), action));  
  }

  protected ProductQueueConsumer(ProductQueue productQueue) {
    this.productQueue = productQueue;
  }

  protected Product getProduct() {
    return productQueue.getItem();
  }
}
