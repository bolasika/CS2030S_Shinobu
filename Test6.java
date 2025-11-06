import cs2030s.fp.InfiniteList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test6 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    Potion[] A1 = new Potion[] {
      new Potion("Healing", 10),
      new Potion("Might", 20)
    };
    Potion[] A2 = new Potion[] {
      new Potion("Healing", 5),
      new Potion("Invisibility", 7)
    };

    InfiniteList<Potion> invA1 = InfiniteList.iterate(0, i -> i + 1)
        .limit(A1.length)
        .map(i -> A1[i]);
    InfiniteList<Potion> invA2 = InfiniteList.iterate(0, i -> i + 1)
        .limit(A2.length)
        .map(i -> A2[i]);

    List<Potion> expectedA = new ArrayList<>();
    expectedA.addAll(Arrays.asList(A1));
    expectedA.addAll(Arrays.asList(A2));

    InfiniteList<Potion> gotA = Task6.mergeIfTarget(invA1, invA2, 42);

    we.expect(
        "Test A1: When total strength equals target, result is not sentinel",
        () -> !gotA.isSentinel(),
        true
    );

    we.expect(
        "Test A2: mergeIfTargetd order is inv1 then inv2 (content equality by name+strength)",
        () -> gotA.toList().equals(expectedA),
        true
    );

    Potion[] B1 = new Potion[] { new Potion("Healing", 3) };
    Potion[] B2 = new Potion[] { new Potion("Might", 4) };

    InfiniteList<Potion> invB1 = InfiniteList.iterate(0, i -> i + 1)
        .limit(B1.length)
        .map(i -> B1[i]);
    InfiniteList<Potion> invB2 = InfiniteList.iterate(0, i -> i + 1)
        .limit(B2.length)
        .map(i -> B2[i]);

    InfiniteList<Potion> gotB = Task6.mergeIfTarget(invB1, invB2, 8);

    we.expect(
        "Test B: When total strength != target, return sentinel()",
        () -> gotB.isSentinel(),
        true
    );


    Potion[] D1 = new Potion[] {
      new Potion("Healing", 1),
      new Potion("Might", 2),
      new Potion("Focus", 3)
    };
    Potion[] D2 = new Potion[] {
      new Potion("Healing", 4),
      new Potion("Might", 5)
    };

    InfiniteList<Potion> invD1 = InfiniteList.iterate(0, i -> i + 1)
        .limit(D1.length)
        .map(i -> D1[i]);
    InfiniteList<Potion> invD2 = InfiniteList.iterate(0, i -> i + 1)
        .limit(D2.length)
        .map(i -> D2[i]);

    List<Potion> expectedD = new ArrayList<>();
    expectedD.addAll(Arrays.asList(D1));
    expectedD.addAll(Arrays.asList(D2));

    InfiniteList<Potion> gotD = Task6.mergeIfTarget(invD1, invD2, 15);

    we.expect(
        "Test D: Order is preserved (inv1 elements first, then inv2)",
        () -> gotD.toList().equals(expectedD),
        true
    );
  }
}
