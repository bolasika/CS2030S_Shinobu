import java.util.*;
import cs2030s.fp.*;

public class Task4 {
    public static int getMaxStrength(String name, InfiniteList<Potion> inventory) {
        return inventory.filter(p -> p.getName().equals(name))
                        .map(p -> p.getStrength())
                        .reduce(0, (x, y) -> Maybe.of(x)
                                                            .filter(a -> a >= y)
                                                            .orElse(y));
    }    
}
