import cs2030s.fp.InfiniteList;
import java.util.List;

public class Test7 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();

    Potion[] A = {
      new Potion("Healing", 3),
      new Potion("Might", 5),
      new Potion("Healing", 4),
      new Potion("Healing", 2)
    };
    InfiniteList<Potion> invA = InfiniteList.iterate(0, i -> i + 1)
        .limit(A.length)
        .map(i -> A[i]);

    we.expect(
        "Test 1: Merge same names -> [Healing(9), Might(5)] in first-appearance order",
        () -> Task7.mergeByName(invA).limit(2).toList().equals(
              List.of(new Potion("Healing", 9), new Potion("Might", 5))),
        true
    );

    Potion[] B = {
      new Potion("Healing", 1),
      new Potion("Invisibility", 2),
      new Potion("Healing", 3),
      new Potion("Might", 4),
      new Potion("Invisibility", 5)
    };
    InfiniteList<Potion> invB = InfiniteList.iterate(0, i -> i + 1)
        .limit(B.length)
        .map(i -> B[i]);

    we.expect(
        "Test 2: Order by first appearance -> [Healing(4), Invisibility(7), Might(4)]",
        () -> Task7.mergeByName(invB).limit(3).toList().equals(
              List.of(new Potion("Healing", 4), new Potion("Invisibility", 7), new Potion("Might", 4))),
        true
    );

    Potion[] C = {
      new Potion("Healing", 1),
      new Potion("Healing", 2),
      new Potion("Healing", 3)
    };
    InfiniteList<Potion> invC = InfiniteList.iterate(0, i -> i + 1)
        .limit(C.length)
        .map(i -> C[i]);

    we.expect(
        "Test 3: All same name -> single merged [Healing 6]",
        () -> Task7.mergeByName(invC).limit(1).toList().equals(
              List.of(new Potion("Healing", 6))),
        true
    );

    /* ===========================================================
     * Test D: All unique names -> unchanged list (order preserved)
     * [P1, Q2, R3] -> [P1, Q2, R3]
     * =========================================================== */
    Potion[] D = {
      new Potion("Healing", 1),
      new Potion("Invisibility", 2),
      new Potion("Might", 3)
    };
    InfiniteList<Potion> invD = InfiniteList.iterate(0, i -> i + 1)
        .limit(D.length)
        .map(i -> D[i]);

    we.expect(
        "Test 4: Unique names",
        () -> Task7.mergeByName(invD).limit(3).toList().equals(
              List.of(new Potion("Healing", 1), new Potion("Invisibility", 2), new Potion("Might", 3))),
        true
    );
  }
}
