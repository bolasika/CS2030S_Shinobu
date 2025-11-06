
import java.util.*;
import cs2030s.fp.*;

public class Task7 {
    public static InfiniteList<Potion> mergeByName(InfiniteList<Potion> inventory) {
        return inventory.flatMap(p ->
            Maybe.of(p)
                 .filter(x -> p == inventory.filter(z -> z.getName().equals(p.getName())).head())   // ensure first instance                 
                 .map(x -> InfiniteList.iterate(0, i -> i + 1).limit(1)                     // IF it's first instance {
                                       .map(i -> new Potion(p.getName(),                            //      InfiniteList[new Potion(name, highest strength)]
                                            inventory.filter(y -> y.getName().equals(p.getName()))
                                                     .map(y -> y.getStrength())
                                                     .reduce(0, (a, b) -> a + b))))
                 .orElse(InfiniteList.sentinel())                                                   // else {sentinel()}
        );
    }
}
