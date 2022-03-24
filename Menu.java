import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private int menuSize;
    private HashMap<Integer, MenuItem> items; //id = key, value = "name, description of how it's prepared, price"
    public void display(){
        for(int i = 1; i <= menuSize; i++) {
            System.out.println(items.get(i));
        }
    }
}
