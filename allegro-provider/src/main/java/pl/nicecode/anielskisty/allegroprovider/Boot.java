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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

public class Boot extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AllegroConfiguration.class);
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AllegroConfiguration.class, args);
    }
}