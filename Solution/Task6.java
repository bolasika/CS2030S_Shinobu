import java.util.*;
import cs2030s.fp.*;

public class Task6 {
    public static InfiniteList<Potion> mergeIfTarget(InfiniteList<Potion> inventory1, InfiniteList<Potion> inventory2, int target) {
        return Maybe.of(
                    inventory1.append(inventory2)
                              .reduce(0, (x, y) -> x + y.getStrength())
                    )
                    .filter(sum -> sum == target)
                    .map(sum -> inventory1.append(inventory2))    
                    .orElse(InfiniteList.sentinel());
    }    
}
