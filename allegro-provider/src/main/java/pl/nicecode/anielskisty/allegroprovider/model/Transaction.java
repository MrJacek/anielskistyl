package pl.nicecode.anielskisty.allegroprovider.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by jhojczak on 4/10/15.
 */
public class Transaction {

    String time;
    String transactionType;
    String buyerLogin;
    int count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBuyerLogin() {
        return buyerLogin;
    }

    public void setBuyerLogin(String buyerLogin) {
        this.buyerLogin = buyerLogin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
