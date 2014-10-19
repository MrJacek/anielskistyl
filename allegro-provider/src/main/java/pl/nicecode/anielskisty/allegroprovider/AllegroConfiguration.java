package pl.nicecode.anielskisty.allegroprovider;

import java.io.File;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.nicecode.anielskisty.allegroprovider.ws.AllegroClient;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AllegroConfiguration extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
////        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/welcome").setViewName("welcome");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/errror").setViewName("errror");
//    }
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("pl.nicecode.allegro.api");
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

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        // keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
        // keytool -list -v -keystore keystore.p12 -storetype pkcs12
        // curl -u user:password -k https://127.0.0.1:9000/greeting
        final String keystoreFile = "Z:\\repo\\anielskistyl\\keystore.p12";
        final String keystorePass = "zaq12wsx";
        final String keystoreType = "PKCS12";
        final String keystoreProvider = "SunJSSE";
        final String keystoreAlias = "tomcat";
        File kFile = new File(keystoreFile);
        System.out.println("\n\n\n"
                + "==============PATH====================="
                + "\n" + kFile.getAbsolutePath() + "\n"
                + "=======================================");

        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addConnectorCustomizers(new MyConnectorCustomizer());
//        (TomcatConnectorCustomizer) (Connector con) -> {
//            con.setScheme("https");
//            con.setSecure(true);
//            Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
//            proto.setSSLEnabled(true);
//            proto.setKeystoreFile(keystoreFile);
//            proto.setKeystorePass(keystorePass);
//            proto.setKeystoreType(keystoreType);
//            proto.setProperty("keystoreProvider", keystoreProvider);
//            proto.setKeyAlias(keystoreAlias);
//        });

        return factory;
    }

    public static class MyConnectorCustomizer implements TomcatConnectorCustomizer {

        final String keystoreFile = "Z:\\repo\\anielskistyl\\keystore.p12";
        final String keystorePass = "zaq12wsx";
        final String keystoreType = "PKCS12";
        final String keystoreProvider = "SunJSSE";
        final String keystoreAlias = "tomcat";

        @Override
        public void customize(Connector con) {
            con.setScheme("https");
            con.setSecure(true);
            Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
            proto.setSSLEnabled(true);
            proto.setKeystoreFile(keystoreFile);
            proto.setKeystorePass(keystorePass);
            proto.setKeystoreType(keystoreType);
            proto.setProperty("keystoreProvider", keystoreProvider);
            proto.setKeyAlias(keystoreAlias);
        }

    }

    @Bean
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public WebSecurityConfigurerAdapter security() {
        return new WebSecurityConfigurerAdapter() {
            @Autowired
            private SecurityProperties security;

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        .antMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated();
                http
                        .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                        .logout()
                        .permitAll();
            }

            @Override
            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.inMemoryAuthentication().withUser("admin").password("admin")
                        .roles("ADMIN", "USER").and().withUser("user").password("user")
                        .roles("USER");
            }
        };
    }
}
