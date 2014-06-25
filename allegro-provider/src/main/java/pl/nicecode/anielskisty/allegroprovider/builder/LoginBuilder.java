/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.builder;

import allegro.DoLoginRequest;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author jhojczak
 */
public class LoginBuilder {

    public DoLoginRequest build(long version) throws IOException {
        DoLoginRequest request = new DoLoginRequest();
        
        Properties prop=new Properties();
        prop.load(LoginBuilder.class.getResourceAsStream("allegro.properties"));
        request.setUserLogin(prop.getProperty("allegro.user"));
        request.setUserPassword(prop.getProperty("allegro.password"));
        request.setCountryCode(1);
        request.setWebapiKey(prop.getProperty("allegro.key"));
        request.setLocalVersion(version);
        return request;
    }

}
