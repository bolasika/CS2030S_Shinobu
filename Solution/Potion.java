/**
 * A Representation of a Potion.
 * 
 * ANSWER for Immutability Potion
 * NOTE: Do NOT make the class final.
 */

public class Potion {
    /** The name of the Potion. */
    private final String name;

    /** The strength level of the Potion. */
    private final int strength;

    /** The number of doses remaining. */
    private int doses;

    /** A flag if the Potion is sealed. */
    private boolean sealed;

    /**
     * Constructor for a Potion.   
     *
     * @param name     The name of the Potion.
     * @param strength The strength level.     
     */
    public Potion(String name, int strength) {
        this.name = name;
        this.strength = strength;       
        this.doses = 50;
        this.sealed = true; // Potions start sealed
    }
        

    /**
     * Return the name of the Potion
     * @return Name of the Potion
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the strength of the Potion
     * @return The strength.
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Gets the remaining doses of the Potion.
     * @return The number of doses.
     */
    public int getDoses() {
        return this.doses;
    }

    /**
     * Checks if the Potion is sealed.
     * @return true if sealed, false otherwise.
     */
    public boolean isSealed() {
        return this.sealed;
    }

    /**
     * Unseal the Potion
     * @return An unsealed Potion.
     */
    public Potion unseal() {
        Potion tmp = new Potion(this.name, this.strength);
        tmp.sealed = false;
        tmp.doses = this.doses;        
        return tmp;
    }

    /**
     * Drinks 'x' doses from the Potion if Potion is unsealed
     * A Potion can only be drunk if it is unsealed
     * and has doses remaining.
     * @param x The number of doses to drink.
     * @return A Potion with reduced doses.
   */
    public Potion drink(int x) {    
        if (!this.sealed && this.doses > 0) {
            Potion tmp = new Potion(this.name, this.strength);
            tmp.sealed = false;
            tmp.doses = Math.max(0, this.doses - x);  
            return tmp;              
        }
        return this;
    }

    /**
     * Enhances the Potion's strength by x       
     * @param x The amount to increase strength by.
     * @return A Potion with increased strength.
     */
    public Potion enhance(int x) {
        Potion tmp = new Potion(this.name, this.strength + x);
        tmp.doses = this.doses;
        tmp.sealed = this.sealed;
        return tmp;                
    }

    /**
     * Dilutes the Potion's strength by x
     * Strength cannot go below 1.         
     *
     * @param x The amount to decrease strength by.
     * @return A Potion with decreased strength.
     */
    public Potion dilute(int x) {
        if (this.strength > 1) {            
            Potion tmp = new Potion(this.name, Math.max(0, this.strength - x));
            tmp.doses = this.doses;
            tmp.sealed = this.sealed;
            return tmp;   
        }
        return this;                
    }

    /**
   * A String representation of a Potion.
   * {Name (S:Str) [D:Dose]} if sealed
   * [Name (S:Str) [D:Dose]] if unsealed   
   * @return The String representation.
   */
    @Override
    public String toString() {
        if (this.sealed) {
            return String.format("{ %s (S:%s) (D:%s) }",this.name, this.strength, this.doses);            
        } else {
            return String.format("[ %s (S:%s) (D:%s) ]", this.name, this.strength, this.doses);            
        }
    }

    /**
   * Checks if two Potions are equal.
   * Two Potions are equal if they have the same name & strength      
   * @param obj The other Potion to compare.
   * @return true if equal, false otherwise.
   */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;        
        }
        if (obj instanceof Potion p) {
            return this.name.equals(p.name) && this.strength == p.strength;
        } 
        return false;
    }

}