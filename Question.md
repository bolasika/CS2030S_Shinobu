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
<br> Note that `Test1.java` does not check all necessary conditions for immutability.


## Task 2 - 5: Functional Style
The following tasks assumes correct implementation of Task 1. You have to write the following methods in Functional Style. Additionally, make sure you make all your methods as flexible as possible unless specified otherwise. You may still get parital mark even if your method is not as flexible as possible.<br>

<b>(Easy) Task 2:</b> Rewrite the static method `Task2.countHealingDoses(InfiniteList<Potion> inventory)` that takes in an <i>InfiniteList of Potion</i> and returns the total amount of doses remaining across all <b>unsealed</b> "Healing" Potion.<br>
<i>You can test the correctness of your FP implementation using `Test2.java`. </i> <br>
<br>

<b>(Easy) Task 3:</b> Rewrite the static method `Task3.getAvgStrength(InfiniteList<Potion> inventory)` that takes in an <i>InfiniteList of Potion</i> and returns the average <b>strength of all potions</b> in the list. <br>
<i>You can test the correctness of your FP implementation using `Test3.java`. </i> <br>
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

<b>(Medium) Task 4:</b> Rewrite the static method `Task4.getMaxStrength(String name, InfiniteList<Potion> inventory)` that takes in a name of the potion and an InfiniteList of Potion, and returns the maximum strength among all potions whose name matches the given name.<br>
<i>You can test the correctness of your FP implementation using `Test4.java`. </i> <br>
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

<b>(Medium) Task 5:</b> Rewrite the static method `Task5.splitStrongPotions(InfiniteList<Potion> inventory)` that returns an <i>InfiniteList of Potion</i> where each potion with strength > 1 is split into two new potions with half the strength of the original potion. Potions with strength == 1 are left unchanged.<br>
For example:
- Healing(6) → Healing(3), Healing(3)
- Might(51) → Might(25), Might(26)     <i> // Take note: If strength is odd, first potion is `floor()` and second potion is `ceil()`</i>
- Invisibility(1) → Invisibility(1) (unchanged)
<br><br>
<i>You can test the correctness of your FP implementation using `Test5.java`.</i>
```jshell
jshell> import cs2030s.fp.*;
jshell> /open Potion.java
jshell> /open Task5.java

jshell> Potion[] potionsA = new Potion[] { new Potion("Healing", 6), new Potion("Might", 51), new Potion("Invisibility", 1) };
potionsA ==> Potion[3] { { Healing (S:6) (D:50) }, { Might (S: ... isibility (S:1) (D:50) } }

jshell> InfiniteList<Potion> ILpotionA = InfiniteList.iterate(0, x -> x + 1).limit(potionsA.length).map(i -> potionsA[i]);
ILpotionA ==> [? ?]

jshell> Task5.splitStrongPotions(ILpotionA).toList().forEach(x -> System.out.println(x.toString()))
{ Healing (S:3) (D:50) }
{ Healing (S:3) (D:50) }
{ Might (S:25) (D:50) }
{ Might (S:26) (D:50) }
{ Invisibility (S:1) (D:50) }
```
