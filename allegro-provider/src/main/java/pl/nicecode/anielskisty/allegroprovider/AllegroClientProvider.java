/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider;

import allegro.DoLoginRequest;
import allegro.DoLoginResponse;
import allegro.DoQuerySysStatusRequest;
import allegro.DoQuerySysStatusResponse;
import allegro.ServicePort;
import allegro.ServiceService;
import com.google.gson.Gson;
import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.WebServiceRef;
import pl.nicecode.anielskisty.allegroprovider.builder.LoginBuilder;

/**
 *
 * @author jhojczak
 */
@Path("/")
public class AllegroClientProvider {

    @WebServiceRef
    private ServiceService service;
    
    private ServicePort client;
    
    
    @PostConstruct
    public void init(){
        client=service.getServicePort();
                
        
    }
    
    @GET
    @Path("/login")
    @Produces({"application/json"})
    public JsonObject login() {
        long versionKey=getVersionKey();
                
        DoLoginRequest request=new LoginBuilder().build(versionKey);
        
     
        DoLoginResponse response=client.doLogin(request);
        
        JsonObjectBuilder builder=Json.createObjectBuilder();
        builder.add("UserId", response.getUserId());
        builder.add("ServerTime", response.getServerTime());
        builder.add("SessionHandlePart", response.getSessionHandlePart());
        return builder.build();
    }

    private long getVersionKey() {
        DoQuerySysStatusRequest r1=new DoQuerySysStatusRequest();
        r1.setCountryId(1);
        r1.setSysvar(1);
        r1.setWebapiKey("sd99aef3");
        DoQuerySysStatusResponse res1=client.doQuerySysStatus(r1);
        return res1.getVerKey();
    }
}
