package com.account.my.common;

import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiClient {

    public static final Logger log = LoggerFactory.getLogger(ApiClient.class.getName());
    private RestTemplate restTemplate;
    protected ResponseEntity<String> responseEntity;
    public String audience;


    public void setAudience(String audience) {
        this.audience = audience;
    }

    public ApiClient(RestTemplate restTemplate, Environment environment){
        this.restTemplate= restTemplate;
        this.restTemplate.setRequestFactory(requestFactory());
        environment.getActiveProfiles();
    }
    public HttpComponentsClientHttpRequestFactory requestFactory()   {
        SSLContext sSlContext=null;
        try{
            sSlContext = SSLContext.getInstance("TLSv1.2");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        TrustManager[] trustManager = new TrustManager[]{
        new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }
        };
        try {
            sSlContext.init(null, trustManager, new SecureRandom());
        }catch (KeyManagementException e){
            e.printStackTrace();
        }

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sSlContext);
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return factory;
    }

    public ResponseEntity<String> exchangeWithoutToken(String url) throws Exception {
        RequestEntity<String> request = new RequestEntity<>(buildHttpHeaderWithJWT(), HttpMethod.GET, new URI(url));
        this.responseEntity = this.restTemplate.exchange(request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> healthCheckActuator(String url) throws Exception {
        RequestEntity<String> request = new RequestEntity<>(buildHttpHeader(), HttpMethod.GET, new URI(url));
        this.responseEntity = this.restTemplate.exchange(request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> exchangeForBodyMsgWithToken(String url, String bodyMsg) throws Exception {
        RequestEntity<String> request = new RequestEntity<>(bodyMsg, buildHttpHeaderWithJWT(), HttpMethod.POST, new URI(url));
        this.responseEntity = this.restTemplate.exchange(request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> exchangeForBodyMsgWithoutToken(String url, String bodyMsg) throws Exception {
        RequestEntity<String> request = new RequestEntity<>(bodyMsg, buildHttpHeader(), HttpMethod.POST, new URI(url));
        this.responseEntity = this.restTemplate.exchange(request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> exchangeForBodyMsgWithoutTokenPut(String url, String bodyMsg) throws Exception {
        RequestEntity<String> request = new RequestEntity<>(bodyMsg, buildHttpHeader(), HttpMethod.PUT, new URI(url));
        this.responseEntity = this.restTemplate.exchange(request, String.class);
        return responseEntity;
    }

    private HttpHeaders buildHttpHeader() throws JSONException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type","application/json");
        headers.set("accept","application/json");
        List<MediaType> mediaList = new ArrayList<>();
        mediaList.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(mediaList);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        return headers;
    }

    private HttpHeaders buildHttpHeaderWithJWT() throws JSONException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type","application/json");
        headers.set("accept","application/json");
        List<MediaType> mediaList = new ArrayList<>();
        mediaList.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(mediaList);
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        return headers;
    }
}
