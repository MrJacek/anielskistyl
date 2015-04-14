/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.builder;

import allegro.api.DoLoginRequest;

/**
 *
 * @author jhojczak
 */
public class LoginBuilder {

    private static final String NOTSET_VALUE = "NOTSET";
    private final String login;
    private final String password;
    private final String key;

    public LoginBuilder(final String key, final String login, final String password) {

        this.login = login;
        this.password = password;
        this.key = key;
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
