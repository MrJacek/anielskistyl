/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

/**
 *
 * @author jhojczak
 */
@RestController
public class AllegroController {

    @Autowired
    AllegroClient client;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "user") String user, @RequestParam(value = "password") String password) {
        System.out.println("client: " + client.toString());
        return client.login(user, password);
    }
}