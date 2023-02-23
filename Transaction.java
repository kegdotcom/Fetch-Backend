import java.sql.Timestamp;

public class Transaction implements Comparable<Transaction> {
    private String payer;
    private int points;
    private Timestamp timestamp;

    /**
     * constructor for a new Transaction object
     * @param line line read from the .csv file with transaction data
     */
    public Transaction (String line) {
        String[] data = line.split(","); //splits the string read from the .csv file into an array of strings {<payer>, <points>, <timestamp>}

        this.payer = data[0].substring(1, data[0].length() - 1); //gets the payer name from the array, and removes the quotation marks from the .csv file
        this.points = Integer.parseInt(data[1]); //gets the points from the array, and converts it to an integer value

        String time = data[2].substring(1, data[2].length() - 2).replace("T", " "); //gets the timestamp from the array, and converts it into a string that is readable by the java Timestamp object
        this.timestamp = Timestamp.valueOf(time); //stores the timestamp as a Timestamp object
    }

    /**
     * getter method for the Transactions payer name
     * @return String of the payer's name
     */
    public String getPayer() {
        return this.payer;
    }

    /**
     * getter method for the Transaction's points
     * @return integer number of points for this transaction
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * getter method for the Transaction's timestamp
     * @return Timestamp object that represents the age of the transaction
     */
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    /**
     * mutator method to increase this Transaction's point value
     * @param pointsAdded number of points to add to this Transaction's value
     * @return the number of points added to this Transaction
     */
    public int addPoints(int pointsAdded) {
        this.points += pointsAdded;
        return pointsAdded;
    }

    /**
     * mutator method to decrease this Transaction's point value
     * @param pointsSubtracted the number of points to subtract from this Transaction's value
     * @return the number of points subtracted from this Transaction
     */
    public int subtractPoints(int pointsSubtracted) {
        this.points -= pointsSubtracted;
        return pointsSubtracted;
    }

    /**
     * mutator method to set this Transaction's point value to zero
     * @return the number of points this Transaction had before
     */
    public int zeroPoints() {
        int old = this.points;
        this.points = 0;
        return old;
    }

    /**
     * overridded compareTo method that determines which is greater or less than based off of timestamp
     * @param other the Transaction object to compare this Transaction to
     * @return negative value if this Transaction was before other, positive if this Transaction was after other, 0 if they have the same timestamp
     */
    @Override
    public int compareTo(Transaction other) {
        return this.timestamp.compareTo(other.getTimestamp());
    }

    /**
     * overridden toString method that prints the Timestamp object in a way that is easy to read
     * @return formatted String representative of this Transaction object
     */
    @Override
    public String toString() {
        return this.payer + ", " + this.points + ", " + this.timestamp;
    }
}