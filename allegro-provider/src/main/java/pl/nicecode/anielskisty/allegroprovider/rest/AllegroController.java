/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

import allegro.api.DoGetMySoldItemsResponse;
import allegro.api.DoGetPostBuyFormsDataForSellersResponse;
import allegro.api.PostBuyFormDataStruct;
import allegro.api.SiteJournalDealsStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.nicecode.anielskisty.allegroprovider.database.dao.DaoTradingPartners;
import pl.nicecode.anielskisty.allegroprovider.database.entity.EntityTradingPartners;
import pl.nicecode.anielskisty.allegroprovider.model.Transaction;
import pl.nicecode.anielskisty.allegroprovider.model.TransactionDescription;
import pl.nicecode.anielskisty.allegroprovider.model.Transactions;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author jhojczak
 */
@RestController
@RequestMapping(value = "api")
public class AllegroController {

    Logger LOG = LoggerFactory.getLogger(AllegroController.class);

    @Autowired
    AllegroClient client;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public AllegroToken login(@RequestParam(value = "key") String key,
                              @RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password) {

        return new AllegroToken(client.login(key, login, password));
    }

    @RequestMapping(value = "/soldItems")
    @ResponseBody
    public DoGetMySoldItemsResponse login(@RequestParam(value = "session") String session) {
        return client.getSoldItems(session);
    }

    @Autowired
    DaoTradingPartners daoTradingPartners;

    @RequestMapping(value = "/sync")
    @ResponseBody
    @Transactional
    public void journalDeals(@RequestParam(value = "session")final String session) {
        Transactions transactions = new Transactions();

        List<SiteJournalDealsStruct> siteJournalDeals = client.getJournalDeals(session).getSiteJournalDeals().getItem();

//        LOG.info("JournalDealsCount: " + siteJournalDeals.size());
//         List<EntityTradingPartners> emailBuyers =siteJournalDeals.stream().filter(item -> item.getDealEventType() == 4).map(item ->
//                 {
//                     PostBuyFormDataStruct form=client.getBuyerData(session, item.getDealTransactionId()).getPostBuyFormData().getItem().stream().findFirst().get();
////                     form.
//
//                 }
//         ).collect(Collectors.toList());

//        LOG.info("Buyer emails "+emailBuyers.toString());
        List<String> msgBuyers =siteJournalDeals.stream().filter(item -> item.getDealEventType() == 4).map(item -> client.getBuyerData(session,
                item.getDealTransactionId()).getPostBuyFormData().getItem().stream().findFirst().get().getPostBuyFormMsgToSeller()).collect(Collectors.toList());

        LOG.info("Buyer Msg "+msgBuyers.toString());
        List<String> cityBuyers =siteJournalDeals.stream().filter(item -> item.getDealEventType() == 4).map(item -> client.getBuyerData(session,
                item.getDealTransactionId()).getPostBuyFormData().getItem().stream().findFirst().get().getPostBuyFormShipmentAddress().getPostBuyFormAdrCity()).collect(Collectors.toList());

        LOG.info("Buyer City "+cityBuyers.toString());

        EntityTradingPartners entityTradingPartners=new EntityTradingPartners();
        entityTradingPartners.setId(UUID.randomUUID());
        entityTradingPartners.setAddressType("SIMPLE");
        entityTradingPartners.setCity("Warsaw");


//        daoTradingPartners.sa
//        for (SiteJournalDealsStruct item : siteJournalDeals) {
//            DoGetPostBuyFormsDataForSellersResponse userData = client.getBuyerData(session,
//                    item.getDealTransactionId());
//
//            Transaction t = new Transaction();
//            String login = "Not found";
//            LOG.info("Form count: " + userData.getPostBuyFormData().getItem().size());
//            if (userData.getPostBuyFormData().getItem().size() < 1) {
//                LOG.info("No post data for deal: " + item.getDealId());
//            } else {
//                login = userData.getPostBuyFormData().getItem().get(0).getPostBuyFormBuyerLogin();
//            }
//            t.setBuyerLogin(login);
//            t.setCount(item.getDealQuantity());
//            t.setTime(LocalDateTime.ofEpochSecond(item.getDealEventTime(),
//                    0,
//                    ZoneOffset.UTC)
//                    .format(DateTimeFormatter.ISO_DATE_TIME));
//            t.setTransactionType(TransactionDescription.valueOfId(item.getDealEventType()).description);
//            transactions.add(t);
//        }
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
