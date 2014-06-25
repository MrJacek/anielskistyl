/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.builder;

import allegro.DoLoginRequest;

/**
 *
 * @author jhojczak
 */
public class LoginBuilder {

    public DoLoginRequest build(long version) {
        DoLoginRequest request = new DoLoginRequest();
        request.setUserLogin("");
        request.setUserPassword("");
        request.setCountryCode(1);
        request.setWebapiKey("");
        request.setLocalVersion(version);
        return request;
    }

}
