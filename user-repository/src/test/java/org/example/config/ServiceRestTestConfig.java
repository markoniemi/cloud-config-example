package org.example.config;

import java.util.Arrays;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.example.repository.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

//@Configuration
//@PropertySource("classpath:server.properties")
public class ServiceRestTestConfig {
    @Value("http://localhost:8082/api/rest/")
//    @Value("${http.protocol}://localhost:${http.port}/user-repository/api/rest/")
    private String baseAddress;
    @Bean
    public List<?> getJsonProviders() {
        return Arrays.asList(new JacksonJaxbJsonProvider());
    }

    @Bean(name = "userService")
    public Object getUserService() {
        return JAXRSClientFactory.create(baseAddress, UserService.class, getJsonProviders());
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer
//    propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}