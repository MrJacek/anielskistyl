/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

import allegro.api.DoGetMySoldItemsResponse;
import allegro.api.DoGetPostBuyDataResponse;
import allegro.api.DoGetSiteJournalDealsResponse;
import allegro.api.SiteJournalDealsStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nicecode.anielskisty.allegroprovider.model.Transaction;
import pl.nicecode.anielskisty.allegroprovider.model.TransactionDescription;
import pl.nicecode.anielskisty.allegroprovider.model.Transactions;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhojczak
 */
@RestController
public class AllegroController {

    @Autowired
    AllegroClient client;

    @RequestMapping(value = "/loginAllegro")
    @ResponseBody
    public AllegroToken login(@RequestParam(value = "key") String key,
                              @RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password) {
        AllegroToken token = new AllegroToken(client.login(key, login, password));
        return token;
    }

    @RequestMapping(value = "/soldItems")
    @ResponseBody
    public DoGetMySoldItemsResponse login(@RequestParam(value = "session") String session) {
        return client.getSoldItems(session);
    }

    @RequestMapping(value = "/journalDeals")
    @ResponseBody
    public Transactions journalDeals(@RequestParam(value = "session") String session) {
        Transactions transactions=new Transactions();

        for( SiteJournalDealsStruct item :client.getJournalDeals(session).getSiteJournalDeals().getItem()) {
            item.getDealId();
            DoGetPostBuyDataResponse userData=client.getBuyPostData(session,item.getDealId());
            Transaction t=new Transaction();
            String login="Not found";
            if(userData.getItemsPostBuyData().getItem().size() > 0){
                if((userData.getItemsPostBuyData().getItem().get(0).getUsersPostBuyData().getItem().size() > 9)){
                     login=userData.getItemsPostBuyData().getItem().get(0).getUsersPostBuyData().getItem().get(0).getUserData().getUserLogin();
                }
            }
            t.setBuyerLogin(login);
            t.setCount(item.getDealQuantity());

            t.setTime(LocalDate.ofEpochDay(item.getDealEventTime()).format(DateTimeFormatter.ISO_DATE));
            t
            t.setTransactionType(TransactionDescription.valueOfId(item.getDealEventType()).description);
            transactions.add(t);
        }
        return transactions;
    }


    @RequestMapping(value = "/transactionType")
    @ResponseBody
    public String getTransactionTypes(@RequestParam(value = "id") int id) {
        String[] types = {"utworzenie aktu zakupowego (deala)",
                "utworzenie formularza pozakupowego (transakcji)",
                "anulowanie formularza pozakupowego (transakcji)",
                "zakończenie (opłacenie) transakcji"};
        if (id <= 0) {
            throw new IllegalArgumentException("Id need to be between 1-4");
        }
        if (id > 4) {
            throw new IllegalArgumentException("Id need to be between 1-4");
        }
        return types[id - 1];
    }
}
