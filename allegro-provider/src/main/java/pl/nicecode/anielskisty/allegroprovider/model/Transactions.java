package pl.nicecode.anielskisty.allegroprovider.model;

import java.util.ArrayList;

/**
 * Created by jhojczak on 4/10/15.
 */
public class Transactions {

    ArrayList<Transaction> transactions;

    public Transactions() {
        this.transactions = new ArrayList<>();
    }

    public void add(Transaction t){
        transactions.add(t);
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
