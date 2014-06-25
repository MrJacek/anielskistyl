/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.magazyn.api;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jhojczak
 */
@Named
@SessionScoped
public class LoginControler {

    public void login() {
        Client client = ClientBuilder.newClient();
        WebTarget myResource = client.target("" + "/overAge");

        
        try {

             myResource.request(MediaType.APPLICATION_JSON).get(null);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return wrapper.getList();
    }
}
