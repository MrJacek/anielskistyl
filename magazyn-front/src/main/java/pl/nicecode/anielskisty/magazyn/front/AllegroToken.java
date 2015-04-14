/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.magazyn.front;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties ;
/**
 *
 * @author jhojczak
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroToken {

    private String token;
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
