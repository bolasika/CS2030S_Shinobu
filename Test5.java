import cs2030s.fp.InfiniteList;
import java.util.List;

public class Test5 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();


    Potion[] potionsA = new Potion[] { new Potion("Healing", 6) };
    InfiniteList<Potion> listA = InfiniteList.iterate(0, x -> x + 1)
        .limit(potionsA.length)
        .map(i -> potionsA[i]);

    List<Potion> outA = Task5.splitStrongPotions(listA).limit(2).toList();

    we.expect(
        "Test A: Even split 6 -> 3,3 (name preserved)",
        () -> outA.size() == 2
            && outA.get(0).getName().equals("Healing")
            && outA.get(1).getName().equals("Healing")
            && outA.get(0).getStrength() == 3
            && outA.get(1).getStrength() == 3,
        true
    );

    Potion[] potionsB = new Potion[] { new Potion("Might", 5) };
    InfiniteList<Potion> listB = InfiniteList.iterate(0, x -> x + 1)
        .limit(potionsB.length)
        .map(i -> potionsB[i]);

    List<Potion> outB = Task5.splitStrongPotions(listB).limit(2).toList();

    we.expect(
        "Test B: Odd split 5 -> 2,3; sum preserved",
        () -> outB.size() == 2
            && outB.get(0).getName().equals("Might")
            && outB.get(1).getName().equals("Might")
            && outB.get(0).getStrength() == 2
            && outB.get(1).getStrength() == 3
            && (outB.get(0).getStrength() + outB.get(1).getStrength()) == 5,
        true
    );


    Potion[] potionsC = new Potion[] { new Potion("Invisibility", 1) };
    InfiniteList<Potion> listC = InfiniteList.iterate(0, x -> x + 1)
        .limit(potionsC.length)
        .map(i -> potionsC[i]);

    List<Potion> outC = Task5.splitStrongPotions(listC).limit(1).toList();

    we.expect(
        "Test C: Strength 1 unchanged",
        () -> outC.size() == 1
            && outC.get(0).getName().equals("Invisibility")
            && outC.get(0).getStrength() == 1,
        true
    );
    
    Potion[] potionsD = new Potion[] {
      new Potion("Healing", 4),
      new Potion("Might", 5),
      new Potion("Invisibility", 1)
    };
    InfiniteList<Potion> listD = InfiniteList.iterate(0, x -> x + 1)
        .limit(potionsD.length)
        .map(i -> potionsD[i]);

    List<Potion> outD = Task5.splitStrongPotions(listD).limit(5).toList();

    we.expect(
        "Test D: Mixed split preserves order and names",
        () -> outD.size() == 5
            && outD.get(0).getName().equals("Healing") && outD.get(0).getStrength() == 2
            && outD.get(1).getName().equals("Healing") && outD.get(1).getStrength() == 2
            && outD.get(2).getName().equals("Might")   && outD.get(2).getStrength() == 2
            && outD.get(3).getName().equals("Might")   && outD.get(3).getStrength() == 3
            && outD.get(4).getName().equals("Invisibility") && outD.get(4).getStrength() == 1,
        true
    );

    
  }
}
