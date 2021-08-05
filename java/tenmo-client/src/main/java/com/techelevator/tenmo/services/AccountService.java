package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountService {


    private String baseUrl;
    private RestTemplate restTemplate=new RestTemplate();

    public AccountService(String url){
        this.baseUrl=url;

    }
    public BigDecimal getBalance(String token){
        BigDecimal balance = BigDecimal.ZERO;
        balance = restTemplate.exchange(baseUrl+"balance" , HttpMethod.GET,makeAuthEntity(token),BigDecimal.class).getBody();
        return balance;

    }

    public List<Transfer> getAllTransfers(String token){
        List<Transfer> transfers = null;
        transfers = Arrays.asList(restTemplate.exchange(baseUrl + "transfers_list", HttpMethod.GET, makeAuthEntity(token), Transfer[].class).getBody());
        return transfers;

    }

    private HttpEntity makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
  /* public Transfer transferHistory(String token){
        Transfer newArr=null;
        newArr=restTemplate.exchange(baseUrl+"trasfer_details",HttpMethod.GET,makeAuthEntity(token), Transfer.class).getBody();
        return newArr;
    }*/
}
