import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {
  public static String getCurrentTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now(); 
    return dtf.format(now).toString();
  }
}
