/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.nicecode.anielskisty.allegroprovider;

/**
 *
 * @author jhojczak
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Boot extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AllegroConfiguration.class);
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AllegroConfiguration.class, args);
    }
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("allegro.api");
        return marshaller;
    }


    @Value("${allegro.url}")
    private String allegroUrl;

    @Bean
    public AllegroClient allegroClient(Jaxb2Marshaller marshaller) {
        AllegroClient client = new AllegroClient();
        client.setDefaultUri(allegroUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}