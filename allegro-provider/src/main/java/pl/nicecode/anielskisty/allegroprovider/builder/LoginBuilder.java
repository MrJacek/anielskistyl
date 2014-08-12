/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.builder;

import allegro.DoLoginRequest;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhojczak
 */
public class LoginBuilder {

    private final String login;
    private final String password;
    private final String key;

    public LoginBuilder() {
        try {
            Properties prop = new Properties();
            prop.load(LoginBuilder.class.getResourceAsStream("allegro.properties"));
            login = prop.getProperty("allegro.user");
            password = prop.getProperty("allegro.password");
            key = prop.getProperty("allegro.key");
        } catch (IOException ex) {
            throw new IllegalStateException("Can't load user cardinals from properties.");
        }
    }

    public DoLoginRequest build(long version) {
        DoLoginRequest request = new DoLoginRequest();
        request.setUserLogin(login);
        request.setUserPassword(password);
        request.setCountryCode(1);
        request.setWebapiKey(key);
        request.setLocalVersion(version);
        return request;
    }

}
