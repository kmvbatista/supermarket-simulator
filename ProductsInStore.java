public class ProductsInStore {

  private static Product[] items = new Product[] {
    new Product("sabão", 5.5f),
    new Product("arroz", 7.3f),
    new Product("feijão", 8.2f),
    new Product("macarrão", 2.5f),
    new Product("carne", 25.5f),
    new Product("café", 10.1f),
    new Product("iogurte", 2.0f),
    new Product("creme dental", 7.6f),
    new Product("abóbora", 2.3f),
    new Product("tomate", 5.7f),
  };

  public static Product getRandomProduct() {
    int itemIndex = Randomer.getRandomIntFromInterval(0, items.length-1);
    return items[itemIndex];
  }
}
