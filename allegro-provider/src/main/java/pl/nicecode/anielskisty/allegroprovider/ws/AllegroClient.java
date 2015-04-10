package pl.nicecode.anielskisty.allegroprovider.ws;

import allegro.api.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import pl.nicecode.anielskisty.allegroprovider.builder.LoginBuilder;

import java.util.logging.Logger;

public class AllegroClient extends WebServiceGatewaySupport {
    private static final Logger LOG = Logger.getLogger(AllegroClient.class.getName());

    private static final int COUNTRY_ID_POLAND = 1;
    private static final int SYSTEM_VAR = 1;
    //    private final String allegroURI = "https://webapi.allegro.pl.webapisandbox.pl/service.php";
    private final String allegroURI = "https://webapi.allegro.pl/service.php";


    public long getVersion(final String key) {
        DoQuerySysStatusRequest request = new DoQuerySysStatusRequest();
        request.setCountryId(COUNTRY_ID_POLAND);
        request.setSysvar(SYSTEM_VAR);

        request.setWebapiKey(key);
        DoQuerySysStatusResponse
                response
                = DoQuerySysStatusResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request,
                new SoapActionCallback(
                        allegroURI + "/DoQuerySysStatusRequest")));
        return response.getVerKey();
    }


    public String login(String key, String login, String password) {
        long verison = getVersion(key);
        System.out.println("Version API:" + verison);
        DoLoginRequest request = new LoginBuilder(key, login, password).build(verison);
        DoLoginResponse response = DoLoginResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request,
                new SoapActionCallback(allegroURI + "/DoLoginRequest")));
        return response.getSessionHandlePart();
    }


    public DoGetMySoldItemsResponse getSoldItems(String sessionHandle) {
        DoGetMySoldItemsRequest request = new DoGetMySoldItemsRequest();
        request.setSessionId(sessionHandle);
        DoGetMySoldItemsResponse
                response
                = DoGetMySoldItemsResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request,
                new SoapActionCallback(allegroURI + "/DoGetMySoldItemsRequest")));
        return response;
    }


    public DoGetSiteJournalDealsResponse getJournalDeals(String sessionHandle) {
        DoGetSiteJournalDealsRequest request = new DoGetSiteJournalDealsRequest();
        request.setSessionId(sessionHandle);
        request.setJournalStart(0L);
        DoGetSiteJournalDealsResponse response =
                DoGetSiteJournalDealsResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(
                        request, new SoapActionCallback(allegroURI + "/doGetSiteJournalDeals")));
        return response;
    }

    public DoGetPostBuyDataResponse getBuyPostData(String sessionHandle,Long dealId) {
        DoGetPostBuyDataRequest request = new DoGetPostBuyDataRequest();
        request.setSessionHandle(sessionHandle);
        ArrayOfLong dealIdArray=new ArrayOfLong();
        dealIdArray.getItem().add(dealId);
        request.setItemsArray(dealIdArray);
                DoGetPostBuyDataResponse response =
                        DoGetPostBuyDataResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(
                        request, new SoapActionCallback(allegroURI + "/goGetPostBuyDataResponse")));
        return response;
    }
}
