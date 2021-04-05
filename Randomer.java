public class Randomer {
  public static int getRandomIntFromInterval(int minimum, int maximum) {
    return (int) Math.floor(Math.random()*(maximum-minimum+1)+minimum);
  }
}
