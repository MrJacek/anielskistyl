/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

/**
 *
 * @author jhojczak
 */
public class AllegroToken {

    String token;

    public AllegroToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
