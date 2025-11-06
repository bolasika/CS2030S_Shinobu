import java.util.*;
import cs2030s.fp.*;

public class Task5 {
    public static InfiniteList<Potion> splitStrongPotions(InfiniteList<Potion> inventory) {
        return inventory.flatMap(
            eachPotion -> 
                Maybe.of(eachPotion)
                     .filter(x -> x.getStrength() > 1)
                     .filter(x-> x.getStrength() % 2 == 0)   
                     .map(x -> InfiniteList.iterate(0, i -> i + 1)
                                           .limit(2)
                                           .map(i -> new Potion(x.getName(), (x.getStrength() / 2)))
                        )
                     .orElse(Maybe.of(eachPotion)
                                  .filter(x -> x.getStrength() > 1)
                                  .map(x -> InfiniteList.iterate(0, i -> i + 1)
                                                        .limit(2)
                                                        .map(i -> new Potion(x.getName(), (x.getStrength() / 2) + i))
                                    ).orElse(InfiniteList.iterate(0, i -> i + 1)
                                                        .limit(1)
                                                        .map(i -> eachPotion)                        
                     )                                                       
                    )
                     
        );
        
    }    
}
