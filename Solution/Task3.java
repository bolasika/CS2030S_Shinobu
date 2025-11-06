import java.util.*;
import cs2030s.fp.*;

public class Task3 {
    public static double getAvgStrength(InfiniteList<Potion> inventory) {
        return inventory.map(p -> p.getStrength())
                        .reduce(0.0, (x, y) -> x + y) / inventory.count();
    }
}