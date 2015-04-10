/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

import allegro.api.DoGetMySoldItemsResponse;
import allegro.api.DoGetSiteJournalDealsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

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
    public DoGetSiteJournalDealsResponse journalDeals(@RequestParam(value = "session") String session) {
        return client.getJournalDeals(session);
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
