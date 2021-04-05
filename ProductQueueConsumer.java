public abstract class ProductQueueConsumer extends Thread {

  private ProductQueue productQueue;
  protected boolean hasfinished;

  protected abstract void finish();

  

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

  protected boolean hasItemToGet() {
    return !this.productQueue.isEmpty();
  }

  protected void spendTimeInSeconds(int seconds) {
    try {
      Thread.sleep(convertSecondsToMilliseconds(seconds));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void spendTime(int minTimeInSeconds, int maxTimeInSeconds) {
    int minTime = convertSecondsToMilliseconds(minTimeInSeconds);
    int maxTime = convertSecondsToMilliseconds(maxTimeInSeconds);
    int timeToSpend = Randomer.getRandomIntFromInterval(minTime, maxTime);
    try {
      Thread.sleep(timeToSpend);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private int convertSecondsToMilliseconds(int seconds) {
    return seconds*=1000;
  }
}
