public class Payer implements Comparable<Payer> {
    private String name;
    private int balance;

    public Payer (String name, int points) {
        this.name = name;
        this.balance = points;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public int addPoints(int points) {
        this.balance += points;
        return points;
    }

    public int subtractPoints(int points) {
        this.balance -= points;
        return points;
    }

    @Override
    public int compareTo(Payer other) {
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return this.name + ": " + this.balance;
    }
}
