package org.cloudconfig.client;

import java.util.Collections;

import javax.annotation.Resource;

import org.cloudconfig.client.config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudConfigClientApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
// @ActiveProfiles("local")
@Log4j2
public class CloudConfigClientApplicationIT {
    @Resource
    private String url;

    @Test
    public void hello() {
        HttpHeaders headers = createHeaders();
        ResponseEntity<String> response = new TestRestTemplate().exchange(url + "/hello/test", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Hello"));
        Assert.assertTrue(response.getBody().contains("test"));
    }
    @Test
    public void discoveryServer() throws InterruptedException {
        Thread.sleep(10000);
        HttpHeaders headers = createHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        log.debug("test discoveryServer");
        ResponseEntity<String> response = new TestRestTemplate().exchange("http://localhost:8081/cloud-config-server/eureka/apps", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("cloud-config-client"));
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        return headers;
    }
}
