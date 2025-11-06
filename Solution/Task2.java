import cs2030s.fp.InfiniteList;

public class Task2 {
    public static int countHealingDoses(InfiniteList<Potion> inventory) {
        return inventory.filter(x -> x.getName().equals("Healing"))
                        .filter(x -> !x.isSealed())
                        .reduce(0, (x, y) -> x + y.getDoses());
    }    
}
