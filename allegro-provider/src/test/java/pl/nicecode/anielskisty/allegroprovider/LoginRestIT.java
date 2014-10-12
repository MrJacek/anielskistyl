/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nicecode.anielskisty.allegroprovider;

import java.util.Arrays;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jhojczak
 */
public class LoginRestIT {

    @Test
    public void shouldDisplaySessionIdAfterLogin() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));

        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> entity = template.getForEntity(
                "https://localhost:8080/allegrologin", String.class);
        
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        String sessionHandle = entity.getBody();

        System.out.println("Session handle: " + sessionHandle);

    }

}
