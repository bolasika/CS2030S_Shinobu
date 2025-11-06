import java.util.*;
import cs2030s.fp.*;

public class Task6 {
    public static InfiniteList<Potion> mergeIfTarget(InfiniteList<Potion> inventory1, InfiniteList<Potion> inventory2, int target) {
        return Maybe.of(                                                    // use maybe since we cannot use coditiatonal / ternary
                    inventory1.append(inventory2)                           // merge them
                              .reduce(0, (x, y) -> x + y.getStrength())     // get total strength
                    )       
                    .filter(sum -> sum == target)                           // If total strength == target {
                    .map(sum -> inventory1.append(inventory2))              //      return merged;}
                    .orElse(InfiniteList.sentinel());                       // else {return sentinel()}
    }    
}
