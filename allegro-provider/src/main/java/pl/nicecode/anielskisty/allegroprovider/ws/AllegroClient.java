package pl.nicecode.anielskisty.allegroprovider.ws;

import allegro.api.DoGetMySoldItemsRequest;
import allegro.api.DoGetMySoldItemsResponse;
import pl.nicecode.anielskisty.allegroprovider.builder.LoginBuilder;
import allegro.api.DoLoginRequest;
import allegro.api.DoLoginResponse;
import allegro.api.DoQuerySysStatusRequest;
import allegro.api.DoQuerySysStatusResponse;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class AllegroClient extends WebServiceGatewaySupport {
    private static final Logger LOG = Logger.getLogger(AllegroClient.class.getName());

    private static final int COUNTRY_ID_POLAND = 1;
    private static final int SYSTEM_VAR = 1;
//    private final String allegroURI="https://webapi.allegro.pl.webapisandbox.pl/service.php";
    private final String allegroURI = "https://webapi.allegro.pl/service.php";

    @Bean
    public long getVersion(final String key) {
        DoQuerySysStatusRequest request = new DoQuerySysStatusRequest();
        request.setCountryId(COUNTRY_ID_POLAND);
        request.setSysvar(SYSTEM_VAR);

        request.setWebapiKey(key);
        DoQuerySysStatusResponse response = DoQuerySysStatusResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(
                allegroURI + "/DoQuerySysStatusRequest")));
        return response.getVerKey();
    }

    @Bean
    public String login(String key,String login, String password) {
        long verison = getVersion(key);
        System.out.println("Version API:"+verison);
        DoLoginRequest request = new LoginBuilder(key,login, password).build(verison);
        DoLoginResponse response = DoLoginResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(allegroURI + "/DoLoginRequest")));
        return response.getSessionHandlePart();
    }

    @Bean
    public DoGetMySoldItemsResponse getSoldItems(String sessionHandle) {
        DoGetMySoldItemsRequest request = new DoGetMySoldItemsRequest();
        request.setSessionId(sessionHandle);
        DoGetMySoldItemsResponse response = DoGetMySoldItemsResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(allegroURI + "/DoGetMySoldItemsRequest")));
        return response;
    }
}
