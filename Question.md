# Instruction to Candidate
1. Solve the programming tasks by editing <i>Potion.java</i> as well as <i>Task2.java to Task5.java</i>. You are not allowed to add additional files
2. Some tasks require <b>functional-style</b>. There should be only a single statement, usually a return statement. No other statements are allowed including declaration of local variables. You should use lambda expression (no anonymous class) without declaring blocks (including adding new methods). You should not have conditionals / ternary and you should have no loop.
3. You are NOT allowed to add any import statement. 
4. You are not allowed to use `toList()` as part of your Functional Programming code solution.
5. You are not allowed to use Java APIs like `Stream` or `List`. The import of `java.util.*` in some Task files is just to write the skeleton code, and your task is to convert it into Functional Code with the APIs implemented in your Programming Exercises.
6. You are allowed to use any API from your programming Exercise 5-7, namely `Maybe`, `Lazy` and `InfiniteList`. The details of the APIs are provided in `.API` files

## Potion
<b> Potion </b> 
<br>
Each Potion has an `name` (String), a `strength` (int), `doses` (int) that is measured by milliliters, and a `sealed` (boolean) flag to check if the flask is sealed or not. 
<br>
By default, the flask only have one size can be filled up to 50ml.
<br> 
You are **NOT** allowed to add new methods, not even private methods. 
- `Potion(String name, int strength)`: Creates a new, sealed potion.
- `String getName()`: Returns the name of the potion.
- `int getStrength()`: Returns the strength of the potion.
- `int getDoses()`: Returns the number of doses remaining.
- `boolean isSealed()`: Returns true if the potion is sealed, false otherwise.
- `Potion unseal()`: Unseals the potion. If already unsealed, it returns itself.
- `Potion drink(int x)`: Drinks $x$ doses from the potion. Can only be drunk if unsealed and doses > 0. If $x$ is more than the remaining doses, doses become 0. If sealed or doses == 0, it returns itself.
- `Potion enhance(int x)`: Increases the strength of the potion by $x$.
- `Potion dilute(int x)`: Decreases the strength of the potion by $x$. Strength cannot go below 1. If strength is already 1, it returns itself.

The `toString()` and `equals()` methods are already implemented for you. You should not modify them. Two Potion objects are considered equal if they have the same name and strength, regardless of doses or sealed status.



## Task 1: Make Potion Immutable
All methods that mutate the class should return a new instance of the class instead. Remember that you are **NOT** allowed to add new methods. You should **NOT** make the Potion class final.

You can test the correctness of your immutability implementation using `Test1.java`. 
<br> Note that Test1.java does not check all necessary conditions for immutability.


## Task 2 - 7: Functional Style
The following tasks assumes correct implementation of Task 1. You have to write the following methods in Functional Style. Additionally, make sure you make all your methods as flexible as possible unless specified otherwise. You may still get parital mark even if your method is not as flexible as possible.<br>

<b>(Easy) Task 2:</b> Rewrite the static method `Task2.countHealingDoses(InfiniteList<Potion> inventory)` that takes in an <i>InfiniteList of Potion</i> and returns the total amount of doses remaining across all <b>unsealed</b> "Healing" Potion.<br>
You can test the correctness of your FP implementation using `Test2.java`. 
<br>

<b>(Easy) Task 3:</b> Rewrite the static method `Task3.getAvgStrength(InfiniteList<Potion> inventory)` that takes in an <i>InfiniteList of Potion</i> and returns the average <b>strength of all potions</b> in the list. <br>
You can test the correctness of your FP implementation using `Test3.java`. <br>
```jshell
jshell> import cs2030s.fp.*;
jshell> /open Potion.java
jshell> /open Task2.java
jshell> Potion[] potionsA = new Potion[] {
   ...>     new Potion("Healing",10).unseal(),
   ...>     new Potion("Healing",20).unseal().drink(7),
   ...>     new Potion("Healing",30),
   ...>     new Potion("Invisibility",30)
   ...> }
potionsA ==> Potion[4] { [ Healing (S:10) (D:50) ], [ Healing  ... sibility (S:30) (D:50) } }
jshell> InfiniteList<Potion> ILpotionA = InfiniteList.iterate(0, x -> x + 1).limit(potionsA.length).map(i -> potionsA[i]);
ILpotionA ==> [? ?]
jshell> Task2.countHealingDoses(ILpotionA);
$29 ==> 93
jshell> // 93 because 50 + 50 - 7 = 93
jshell> /open Task3.java
jshell> Task3.getAvgStrength(ILpotionA)
$33 ==> 22.5
jshell> // (10 + 20 + 30 + 30) / 4 = 22.5
```

(Medium) Task 4: Rewrite the static method `Task4.getMaxStrength(String name, InfiniteList<Potion> inventory)` that takes in a name of the potion and an InfiniteList of Potion, and returns the maximum strength among all potions whose name matches the given name.<br>
You can test the correctness of your FP implementation using `Test4.java`. <br>
```jshell
jshell> import cs2030s.fp.*;
jshell> /open Potion.java
jshell> /open Task4.java
jshell> Potion[] potionsA = new Potion[] {
   ...>           new Potion("Healing", 10),
   ...>           new Potion("Healing", 25),
   ...>           new Potion("Might", 40),
   ...>           new Potion("Healing", 18)
   ...>     };
potionsA ==> Potion[4] { { Healing (S:10) (D:50) }, { Healing  ...  Healing (S:18) (D:50) } }
jshell> InfiniteList<Potion> ILpotionA = InfiniteList.iterate(0, x -> x + 1).limit(potionsA.length).map(i -> potionsA[i]);
ILpotionA ==> [? ?]
jshell> Task4.getMaxStrength("Healing", ILpotionA)
$48 ==> 25
```

(Medium) Task 5: Rewrite the static method `Task5.splitStrongPotions(InfiniteList<Potion> inventory)` that returns an <i>InfiniteList of Potion</i> where each potion with strength > 1 is replaced by two new potions whose strengths sum to the original strength. Potions with strength == 1 are left unchanged.<br>
For example:
- Healing(6) → Healing(3), Healing(3)
- Might(5) → Might(2), Might(3)
- Invisibility(1) → Invisibility(1) (unchanged)
<br><br>
You can test the correctness of your FP implementation using `Test5.java`. <br>

```jshell
jshell> import cs2030s.fp.*;
jshell> /open Potion.java
jshell> /open Task5.java
jshell> Potion[] potionsA = new Potion[] { new Potion("Healing", 6), new Potion("Might", 50), new Potion("Invisibility", 1) };
potionsA ==> Potion[3] { { Healing (S:6) (D:50) }, { Might (S: ... isibility (S:1) (D:50) } }
jshell> InfiniteList<Potion> ILpotionA = InfiniteList.iterate(0, x -> x + 1).limit(potionsA.length).map(i -> potionsA[i]);
ILpotionA ==> [? ?]
jshell> Task5.splitStrongPotions(ILpotionA).toList().forEach(x -> System.out.println(x.toString()))
{ Healing (S:3) (D:50) }
{ Healing (S:3) (D:50) }
{ Might (S:25) (D:50) }
{ Might (S:25) (D:50) }
{ Invisibility (S:1) (D:50) }
```

(Medium) Task 6: Write the static method `Task6.mergeIfTarget(InfiniteList<Potion> inventory1, InfiniteList<Potion> inventory2, int target)` that takes in two <i>InfiniteList<Potion> and an integer target</i>, and returns a merged InfiniteList<Potion> containing all potions from both inventories if the total combined strength equals the target. <br>
If the total combined strength does not equal the target, the method should return sentinel.<br>
You can test the correctness of your FP implementation using `Test6.java`. <br>

```jshell
jshell> /open Potion.java
jshell> import cs2030s.fp.*;
jshell> /open Task6.java

jshell> Potion[] I1 = new Potion[] {
   ...>           new Potion("Healing", 1),
   ...>           new Potion("Might", 2),
   ...>           new Potion("Focus", 3)
   ...>     };
I1 ==> Potion[3] { { Healing (S:1) (D:50) }, { Might (S: ... , { Focus (S:3) (D:50) } }

jshell> Potion[] I2 = new Potion[] {
   ...>           new Potion("Healing", 4),
   ...>           new Potion("Might", 5)
   ...>     };

jshell> InfiniteList<Potion> ILI1 = InfiniteList.iterate(0, i -> i + 1).limit(I1.length).map(i -> I1[i]);
ILI1 ==> [? ?]
jshell> InfiniteList<Potion> ILI2 = InfiniteList.iterate(0, i -> i + 1).limit(I2.length).map(i -> I2[i]);
ILI2 ==> [? ?]

jshell> InfiniteList<Potion> IL3 = Task6.mergeIfTarget(ILI1, ILI2, 15);
IL3 ==> [[{ Healing (S:1) (D:50) }] ?]

jshell> IL3.isSentinel() == false;
$16 ==> true

jshell> IL3.reduce(0, (x, y) -> x + y.getStrength()) == 15; // should return 15
$17 ==> true

jshell> InfiniteList<Potion> IL4 = Task6.mergeIfTarget(ILI1, ILI2, 16);
IL4 ==> -

jshell> IL4.isSentinel() == true;
$20 ==> true
```

(Hard) Task 7: Write the static method `Task7.mergeByName(InfiniteList<Potion> inventory)` that takes <i>an InfiniteList<Potion></i> and returns an InfiniteList<Potion> where all potions with the <b>same name</b> are merged into a single potion whose strength is the sum of their strengths. (doses are ignored here)
For example:
- [Healing(3), Might(5), Healing(4), Healing(2)] → [Healing(9), Might(5)]
```jshell
jshell> import cs2030s.fp.*;
jshell> /open Potion.java
jshell> /open Task7.java

jshell> Potion[] A = {
   ...>     new Potion("Healing", 3),
   ...>     new Potion("Might", 5),
   ...>     new Potion("Healing", 4),
   ...>     new Potion("Healing", 2)
   ...> };
A ==> Potion[4] { { Healing (S:3) (D:50) }, { Might (S: ... { Healing (S:2) (D:50) } }

InfiniteList<Potion> ILA = InfiniteList.iterate(0, i -> i + 1).limit(A.length).map(i -> A[i]);
ILA ==> [? ?]

jshell> List<Potion> A_merged = List.of(new Potion("Healing", 9), new Potion("Might", 5));
A_merged ==> [{ Healing (S:9) (D:50) }, { Might (S:5) (D:50) }]

jshell> Task7.mergeByName(ILA).limit(2).toList().equals(A_merged)
$24 ==> true
```
You can test the correctness of your FP implementation using `Test7.java`. <br>

<details>
  <summary>Hint for Task 7</summary>
  
  1. If you are given InfiniteList of 3 Heal Potion, you just need 1, how do you do that? Find the first instance with `head()`! 
  2. Supposed (1) is completed, how are you going to add up all of the sum for the same Potion and return it? 
  
</details>