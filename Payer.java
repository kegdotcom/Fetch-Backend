public class Payer implements Comparable<Payer> {
    private String name;
    private int balance;

    /**
     * constructor for Payer objects
     * @param name the name of the payer organization
     * @param points the points for the new Payer object to start with
     */
    public Payer (String name, int points) {
        this.name = name;
        this.balance = points;
    }

    /**
     * getter method for the Payer corporation name
     * @return the payer corporation's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter method for the Payer account balance
     * @return the current balance of this Payer
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * mutator method to increase the Payer account balance
     * @param points the number of points to add to the Payer account balance
     * @return the number of points added to the account
     */
    public int addPoints(int points) {
        this.balance += points;
        return points;
    }

    /**
     * mutator method to decrease the Payer account balance
     * @param points the number of points to subtract from the Payer account balance
     * @return the number of points subtracted from the account
     */
    public int subtractPoints(int points) {
        this.balance -= points;
        return points;
    }

    /**
     * overridden compareTo method for Payer objects to be compared against each other
     * @param other the Payer object to be compared to this Payer
     * @return a negative value if this payer is alphabetically before other, a positive value if this payer is alphabetically after other, and 0 if the names are equal
     */
    @Override
    public int compareTo(Payer other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * overridden toString method to get a String representation of this Payer
     * @return a formatted string that represents this Payer account
     */
    @Override
    public String toString() {
        return this.name + ": " + this.balance;
    }
}
