/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.magazyn.front;

/**
 *
 * @author jhojczak
 */
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.web.client.RestTemplate;

@ManagedBean
public class AllegroConfig implements Serializable {

    
    private static final long serialVersionUID = 822239550726735593L;

    String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassowrod() {
        return passoword;
    }

    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }

    String passoword;

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void loginToAllegro() {
//        RestTemplate restTemplate = new RestTemplate();    
//        AllegroToken token = restTemplate.getForObject("http://localhost:8084/loginAllegro?user="+login+"&"+passoword, AllegroToken.class);
//        System.out.println("token: " + token.toString());
    }

}
