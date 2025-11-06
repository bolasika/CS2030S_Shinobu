import cs2030s.fp.InfiniteList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test6 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    /* ===========================================================
     * Test A: Sum equals target → returns mergeIfTargetd (inv1 ++ inv2)
     * inv1 = [Healing(10), Might(20)]
     * inv2 = [Healing(5), Invisibility(7)]
     * total = 10+20+5+7 = 42 → target 42
     * =========================================================== */
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

    /* ===========================================================
     * Test B: Sum NOT equal to target → returns sentinel()
     * inv1 = [Healing(3)]
     * inv2 = [Might(4)]
     * total = 7 → target 8 → sentinel
     * =========================================================== */
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


    /* ===========================================================
     * Test D: Order preservation with mixed names
     * inv1 = [H(1), M(2), F(3)]
     * inv2 = [H(4), M(5)]
     * total = 1+2+3+4+5 = 15 → target 15
     * Expect mergeIfTargetd order: inv1 then inv2 exactly
     * =========================================================== */
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
