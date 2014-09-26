package pl.nicecode.anielskisty.allegroprovider.ws;

import pl.nicecode.anielskisty.allegroprovider.builder.LoginBuilder;
import allegro.api.DoLoginRequest;
import allegro.api.DoLoginResponse;
import allegro.api.DoQuerySysStatusRequest;
import allegro.api.DoQuerySysStatusResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class AllegroClient extends WebServiceGatewaySupport {

    private static final int COUNTRY_ID_POLAND = 1;
    private static final int SYSTEM_VAR = 1;

    @Bean
    public long getVersion() {
        DoQuerySysStatusRequest request = new DoQuerySysStatusRequest();
        request.setCountryId(COUNTRY_ID_POLAND);
        request.setSysvar(SYSTEM_VAR);
        request.setWebapiKey("sd99aef3");
        DoQuerySysStatusResponse response = DoQuerySysStatusResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(
                "https://webapi.allegro.pl.webapisandbox.pl/service.php/DoQuerySysStatusRequest")));
        return response.getVerKey();
    }


    @Bean
    public String login(String login,String password) {
        long verison = getVersion();
        DoLoginRequest request = new LoginBuilder(login,password).build(verison);
        DoLoginResponse response = DoLoginResponse.class.cast(getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("https://webapi.allegro.pl.webapisandbox.pl/service.php/DoLoginRequest")));
        return response.getSessionHandlePart();
    }
}
