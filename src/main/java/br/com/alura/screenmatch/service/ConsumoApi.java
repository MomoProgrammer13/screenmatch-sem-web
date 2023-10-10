package br.com.alura.screenmatch.service;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static javax.xml.crypto.dsig.Transform.BASE64;
@Component
public class ConsumoApi {

    @Value("${proxy.username}")
    String username;
    @Value("${proxy.pass}")
    String pass;

    @Value("${proxy.host}")
    String proxyHost;
    @Value("${proxy.port}")
    int port;


    HttpClient client = new HttpClient();
    public ConsumoApi(){



    }
    public String obterDados(String endereco) {

            HostConfiguration config = client.getHostConfiguration();
            config.setProxy(proxyHost,port);

            Credentials credentials = new UsernamePasswordCredentials(username,pass);
            AuthScope authScope = new AuthScope(proxyHost,port);

            client.getState().setProxyCredentials(authScope,credentials);


        HttpMethod method = new GetMethod(endereco);


        
        String response = null;
        
        try {
            client.executeMethod(method);
            
            if(method.getStatusCode() == HttpStatus.SC_OK){
                response = method.getResponseBodyAsString();
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

}
