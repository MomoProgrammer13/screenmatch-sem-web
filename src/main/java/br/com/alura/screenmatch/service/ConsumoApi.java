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

import static javax.xml.crypto.dsig.Transform.BASE64;

public class ConsumoApi {

    HttpClient client = new HttpClient();
    public ConsumoApi(){


        HostConfiguration config = client.getHostConfiguration();
        config.setProxy(proxyHost,port);

        Credentials credentials = new UsernamePasswordCredentials(username,pass);
        AuthScope authScope = new AuthScope(proxyHost,port);

        client.getState().setProxyCredentials(authScope,credentials);

    }
    public String obterDados(String endereco) {



//        //Config Proxy
//        //true para passar pelo servidor proxy
//        System.setProperty("proxySet", "true");
//
//        //IP ou nome do servidor proxy
//        System.setProperty("http.proxyHost", proxyHost);
//
//        //Porta do proxy
//        System.setProperty("http.proxyPort", String.valueOf(port));
//
//        System.setProperty("http.proxyUser", username);
//        System.setProperty("http.proxyPassword", pass);



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

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(endereco))
//                .build();
//        HttpResponse<String> response = null;
//        try {
//            response = client
//                    .send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        
        return response;
    }

}
