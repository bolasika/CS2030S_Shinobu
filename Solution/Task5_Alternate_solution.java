/*
 * Suggested answer from Alex Chien (10B)
 */
import java.util.*;
import cs2030s.fp.*;

public class Task5 {
    public static InfiniteList<Potion> splitStrongPotions(InfiniteList<Potion> inventory) {
        return inventory
        // Convert each potion into an infinite list of itself/2 halves
                        .flatMap(p ->  
        // For each InfiniteList(potion)
                                    Maybe.of(InfiniteList.generate(() -> p).limit(1))
              // v is the infinite list itself, we need to do v.head() to access the potion
                                         .filter(v -> v.head().getStrength() > 1)
              // Create an infiniteList of floor, then append the one with ceil
                                         .map(v -> InfiniteList.generate(() -> p).limit(1).map(a -> a.dilute((int) Math.ceil(a.getStrength() / 2.)))
                                                    .append(InfiniteList.generate(() -> p).limit(1).map(a -> a.dilute((int) Math.floor(a.getStrength() / 2.))))
                                         )
              // Or else keep potion unchanged, wrap in an infiniteList
                                         .orElse(InfiniteList.generate(() -> p).limit(1)
                                    )
         );

        
    }    
}
