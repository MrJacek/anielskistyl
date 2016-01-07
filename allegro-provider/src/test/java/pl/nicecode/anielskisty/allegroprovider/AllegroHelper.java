package pl.nicecode.anielskisty.allegroprovider;

import allegro.api.DoGetSellFormFieldsExtRequest;
import allegro.api.DoGetSellFormFieldsExtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by jhojczak on 12/7/15.
 */
@Component
public class AllegroHelper extends WebServiceGatewaySupport {
//    private final String allegroURI = "https://webapi.allegro.pl.webapisandbox.pl/service.php";
    private static final int COUNTRY_ID_POLAND = 1;
    private static final long SYSTEM_VAR = 1;

    @Value("${allegro.password}")
    String password;

    @Value("${allegro.login}")
    String login;

    @Value("${allegro.key}")
    String key;

    @Value("${allegro.url}")
    String allegroURI;


    public DoGetSellFormFieldsExtResponse doGetSellFormFieldsExt(String key){
        DoGetSellFormFieldsExtRequest request=new DoGetSellFormFieldsExtRequest();
        request.setCountryCode(COUNTRY_ID_POLAND);
        request.setLocalVersion(SYSTEM_VAR);
        request.setWebapiKey(key);
        return DoGetSellFormFieldsExtResponse.class.cast(
            getWebServiceTemplate().marshalSendAndReceive(allegroURI+"/doGetSellFormFieldsExt",request));
    }
}


