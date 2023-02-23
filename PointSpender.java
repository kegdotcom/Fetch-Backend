import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;

public class PointSpender {

    /**
     * method to get the data from each transation in a .csv file, and return this information as an ArrayList of Transaction objects for each transaction in the file
     * @param filepath the path to the .csv file to be read
     * @return an ArrayList of Transaction objects with formatted data from the .csv file
     * @throws FileNotFoundException if the file path provided does not lead to a valid file
     */
    public static ArrayList<Transaction> getData(String filepath) throws FileNotFoundException {
        File data = new File(filepath);
        Scanner scanner = new Scanner(data);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        
        scanner.nextLine();
        while (scanner.hasNextLine()) { //go through the .csv file line by line
            String[] line = scanner.nextLine().split(","); //convert each line into an array of strings, one for each column of the data
            Transaction transaction = new Transaction(line); //create a new Transaction object, which will convert all the Strings retreived from the .csv file into their correct formats
            transactions.add(transaction); //add each Transaction to an ArrayList of Transactions to be returned
        }

        scanner.close(); //close the scanner to prevent any resource leaks
        
        return transactions;
    }

    public static ArrayList<Payer> combineTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Payer> payers = new ArrayList<Payer>();

        for (int i = 1; i < transactions.size(); i++) {
            boolean foundMatch = false;

            //iterates through the payers and checks if there is already a payer object that coresponds to the current transaction
            for (int j = 0; j < payers.size(); j++) {
                if (transactions.get(i).getPayer().equals(payers.get(j).getName())) {
                    foundMatch = true;
                    payers.get(j).addPoints(transactions.get(i).getPoints()); //if a match is found, add the transactions points to that payer's account
                }
            }

            if (!foundMatch) {
                payers.add(new Payer(transactions.get(i).getPayer(), transactions.get(i).getPoints())); //if no match is found, add a new payer account to the arraylist with that transactions points
            }
        }

        return payers;
    }


    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions = getData("./transactions.csv"); //switch to args[1] before submit
        } catch (FileNotFoundException e) {
            System.out.println("No file found in current directory");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        transactions.sort(Comparator.naturalOrder());
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        //finds any payer accounts with negative points and balances that transaction to 0 points, adding their old balance to the points to spend
        // ArrayList<Payer> payers = combineTransactions(transactions);
        // for (int i = 0; i < payers.size(); i++) {
        //     if (payers.get(i).getBalance() < 0) {
        //         points += Math.abs(payers.get(i).addPoints(Math.abs(payers.get(i).getBalance())));
        //     }
        // }

        int pointsToSpend = Integer.parseInt("5000");
        //iterates through the list of transactions from oldest to newest and subtracts points from those accounts as needed
        for (int i = 0; i < transactions.size(); i++) {
            if (pointsToSpend > transactions.get(i).getPoints()) {
                pointsToSpend -= transactions.get(i).zeroPoints();
            } else {
                pointsToSpend -= transactions.get(i).subtractPoints(pointsToSpend);
            }
        }

        //after all the points have been spent, print all the payer accounts and their remaining balances
        // for (Transaction t : transactions) {
        //     System.out.println(t);
        // }
        ArrayList<Payer> end = combineTransactions(transactions);
        end.sort(Comparator.naturalOrder());
        for (Payer p : end) {
            System.out.println(p);
        }
    }
}
