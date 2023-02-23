import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
        
        scanner.nextLine(); //skips the line in the file with column names

        //iterate through the .csv file line by line, and create a new Transaction object for each line, storing each one into an ArrayList
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        while (scanner.hasNextLine()) {
            Transaction transaction = new Transaction(scanner.nextLine());
            transactions.add(transaction);
        }

        scanner.close(); //close the scanner to prevent any resource leaks
        
        return transactions; //return the ArrayList with all of the Transactions
    }

    /**
     * method to combine all Transactions in an ArrayList with the same payer name into a new ArrayList with each combined Payer account
     * @param transactions ArrayList of Transaction objects for the user
     * @return an ArrayList of all the Payers on the user's account and their respective balances
     */
    public static ArrayList<Payer> combineTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Payer> payers = new ArrayList<Payer>();

        //iterate through all the transactions in the provided ArrayList
        for (int i = 0; i < transactions.size(); i++) {
            boolean foundMatch = false;

            //iterates through the payers and checks if there is already a payer object that coresponds to the current transaction
            for (int j = 0; j < payers.size(); j++) {
                //checks to see if there is already a Payer object with that Transaction's payer name
                if (transactions.get(i).getPayer().equals(payers.get(j).getName())) {
                    foundMatch = true;
                    payers.get(j).addPoints(transactions.get(i).getPoints()); //if a match is found, add the transactions points to that payer's account
                }
            }

            //if no match is found, add a new payer account to the arraylist with that transactions points
            if (!foundMatch) {
                payers.add(new Payer(transactions.get(i).getPayer(), transactions.get(i).getPoints())); 
            }
        }

        return payers;
    }


    public static void main(String[] args) {
        //store first argument as the points to spend
        int pointsToSpend = Integer.parseInt("5000");

        //store the transactions found in the first argument, the .csv file, as an arraylist of transactions
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions = getData("./transactions.csv");
        } catch (FileNotFoundException e) {
            System.out.println("No file found in current directory");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //sort the arraylist of transactions by timestamp
        transactions.sort(Comparator.naturalOrder());

        //iterates through the list of transactions from oldest to newest and subtracts points from those accounts as needed
        for (int i = 0; i < transactions.size(); i++) {
            if (pointsToSpend > transactions.get(i).getPoints()) {
                pointsToSpend -= transactions.get(i).zeroPoints();
            } else {
                pointsToSpend -= transactions.get(i).subtractPoints(pointsToSpend);
            }
        }

        //combine all the transactions for each payer into a single payer object with that payer's total points, and stores all the payers into an arraylist
        ArrayList<Payer> end = combineTransactions(transactions);
        end.sort(Comparator.naturalOrder()); //sort the payer accounts alphabetically

        //print the final balances of each Payer
        for (Payer p : end) {
            System.out.println(p);
        }
    }
}
