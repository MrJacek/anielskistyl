/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.nicecode.anielskisty.allegroprovider;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.nicecode.anielskisty.allegroprovider.database.dao.DaoTradingPartners;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author jhojczak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Boot.class)
@WebAppConfiguration
//@TestPropertySource("file:config/application-test.yaml")
public class SyncAllegroTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private AllegroHelper allegroHelper;

    private MockMvc mockMvc;

    Logger LOG = LoggerFactory.getLogger(SyncAllegroTest.class);

    @Value("${allegro.password}")
    String password;

    @Value("${allegro.login}")
    String login;

    @Value("${allegro.key}")
    String key;

    private static String session=null;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        LOG.info("login: "+login);
        LOG.info("key: "+key);
        LOG.info("password: "+password);
    }

    private String login() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/login")
                .param("login", login)
                .param("password", password)
                .param("key", key)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        LOG.info("Response content: " + result.getResponse().getContentAsString());
        Assert.assertFalse("Login request faild", result.getResponse().getContentAsString().isEmpty());
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(result.getResponse().getContentAsString());
        return JsonPath.read(document, "$.token");
    }


    @Test
    public void shouldLoginSuccessful() throws Exception {
        session=login();
    }
    @Autowired
    DaoTradingPartners daoTradingPartners;

    @Test
    public void shouldSyncDatabase() throws Exception {
        String token= session == null ? login() : session;
        Assert.assertFalse("Login unsuccessful!",token == null || token.isEmpty());
        MvcResult result = this.mockMvc.perform(get("/api/sync")
                .param("session", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        daoTradingPartners.findAll().forEach(d ->{
            System.out.println(d.getCity());
            System.out.println(d.getAddressType());
            System.out.println(d.getEmail());
            System.out.println(d.getLogin());
        });

    }
}


