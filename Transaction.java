import java.sql.Timestamp;

public class Transaction implements Comparable<Transaction> {
    private String payer;
    private int points;
    private Timestamp timestamp;

    public Transaction (String[] data) {
        this.payer = data[0].substring(1, data[0].length() - 1);
        this.points = Integer.parseInt(data[1]);
        this.timestamp = Timestamp.valueOf(data[2].substring(1, data[2].length() - 2).replace("T", " "));
    }

    public String getPayer() {
        return this.payer;
    }

    public int getPoints() {
        return this.points;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public int addPoints(int pointsAdded) {
        this.points += pointsAdded;
        return pointsAdded;
    }

    public int subtractPoints(int pointsSubtracted) {
        this.points -= pointsSubtracted;
        return pointsSubtracted;
    }

    public int zeroPoints() {
        int old = this.points;
        this.points = 0;
        return old;
    }

    @Override
    public int compareTo(Transaction other) {
        return this.timestamp.compareTo(other.getTimestamp());
    }

    @Override
    public String toString() {
        return this.payer + ", " + this.points + ", " + this.timestamp;
    }
}