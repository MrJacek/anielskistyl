/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

import allegro.api.DoGetMySoldItemsResponse;
import allegro.api.DoGetPostBuyFormsDataForSellersResponse;
import allegro.api.SiteJournalDealsStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nicecode.anielskisty.allegroprovider.model.Transaction;
import pl.nicecode.anielskisty.allegroprovider.model.TransactionDescription;
import pl.nicecode.anielskisty.allegroprovider.model.Transactions;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author jhojczak
 */
@RestController
public class AllegroController {

    Logger LOG = LoggerFactory.getLogger(AllegroController.class);
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
        Transactions transactions = new Transactions();

        List<SiteJournalDealsStruct> siteJournalDeals = client.getJournalDeals(session).getSiteJournalDeals().getItem();

        LOG.debug("JournalDealsCount: " + siteJournalDeals.size());

        for (SiteJournalDealsStruct item : siteJournalDeals) {
            DoGetPostBuyFormsDataForSellersResponse userData = client.getBuyerData(session,
                    item.getDealTransactionId());

            Transaction t = new Transaction();
            String login = "Not found";
            LOG.debug("Form count: " + userData.getPostBuyFormData().getItem().size());
            if (userData.getPostBuyFormData().getItem().size() < 1) {
                LOG.debug("No post data for deal: " + item.getDealId());
            } else {
                login = userData.getPostBuyFormData().getItem().get(0).getPostBuyFormBuyerLogin();
            }
            t.setBuyerLogin(login);
            t.setCount(item.getDealQuantity());
            t.setTime(LocalDateTime.ofEpochSecond(item.getDealEventTime(),
                    0,
                    ZoneOffset.UTC)
                    .format(DateTimeFormatter.ISO_DATE_TIME));
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
