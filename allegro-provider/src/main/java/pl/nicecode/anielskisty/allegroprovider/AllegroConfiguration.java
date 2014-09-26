package pl.nicecode.anielskisty.allegroprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AllegroConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("allegro.api");
        return marshaller;
    }

    @Bean
    public AllegroClient allegroClient(Jaxb2Marshaller marshaller) {
        AllegroClient client = new AllegroClient();
        client.setDefaultUri("https://webapi.allegro.pl.webapisandbox.pl/service.php");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

//    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private SecurityProperties security;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
//                    .loginPage("/login").failureUrl("/login?error").permitAll();
//        }
//
//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().withUser("admin").password("admin")
//                    .roles("ADMIN", "USER").and().withUser("user").password("user")
//                    .roles("USER");
//        }
//    }
}
