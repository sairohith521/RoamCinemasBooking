package BookMyShow;

public class Utils {
  public static String formatedName(String name) {
        String updatedName=name;
        int len=name.length();
        while(len<10){
            updatedName+=" ";
            len++;
        }
          return updatedName;
    }
}

