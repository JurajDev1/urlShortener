package com.urlshortener.application.controller;

import com.urlshortener.application.Helper;
import com.urlshortener.application.dto.CreateAccountRequest;
import com.urlshortener.application.dto.CreateAccountResponse;
import com.urlshortener.application.dto.RegisterUrlRequest;
import com.urlshortener.application.dto.RegisterUrlResponse;
import com.urlshortener.application.model.Account;
import com.urlshortener.application.model.Link;
import com.urlshortener.application.repository.AccountRepository;
import com.urlshortener.application.repository.LinkRepository;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ApiController {

    public static final String YOUR_ACCOUNT_IS_OPENED_PASSWORD = "Your account is opened, password:";
    public static final String LOCALHOST = "http://localhost:8080/";
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LinkRepository linkRepository;


    @PostMapping("/account")
    CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        String accountId = Jsoup.clean(request.getAccountId(), Safelist.none());
        Account account = new Account();
        account.setAccountId(accountId);

        CreateAccountResponse createAccountResponse = new CreateAccountResponse();

        if (accountRepository.findByAccountId(accountId).size() == 0) {
            try {
                String password = Helper.generatePassword(8);
                account.setPassword(password);
                accountRepository.save(account);
                createAccountResponse.setSuccess(true);
                createAccountResponse.setDescription(YOUR_ACCOUNT_IS_OPENED_PASSWORD + "'" + password + "'");
                return createAccountResponse;
            } catch (Exception e) {
                createAccountResponse.setDescription("Account creation failed.");
                return createAccountResponse;
            }
        } else {
            return createAccountResponse;
        }
    }

    @PostMapping("/register")
    RegisterUrlResponse registerURL(@RequestHeader String accountId, @RequestHeader String password, @RequestBody RegisterUrlRequest request) {
        if (authenticate(accountId, password)) {

            Link link = new Link();
            link.setLongUrl(Jsoup.clean(request.getURL(), Safelist.none()));
            link.setAccountId(Jsoup.clean(accountId, Safelist.none()));
            link.setRedirectType(request.getRedirectType());
            linkRepository.save(link);
            String shortUrl = Helper.generateShortURL((int) (link.getId() * 1000000));
            link.setShortUrl(shortUrl);
            linkRepository.save(link);

            RegisterUrlResponse registerUrlResponse = new RegisterUrlResponse();
            registerUrlResponse.setShortURL(LOCALHOST + shortUrl);
            return registerUrlResponse;
        } else {
            throw new RuntimeException("Could not authenticate user for url registration");
        }
    }

    @GetMapping("/statistic/{AccountId}")
    HashMap<String,Integer> statistic(@RequestHeader String accountId, @RequestHeader String password) {
        if (authenticate(accountId, password)) {
            HashMap<String,Integer> links = new HashMap<>();
            for (Link link : linkRepository.findByAccountId(accountId)) {
                links.put(link.getLongUrl(),link.getLinkUsed());
            }
            return links;
        } else {
            throw new RuntimeException("Could not authenticate user for statistics");

        }
    }

    private boolean authenticate(String accountId, String password) {
        var accounts = accountRepository.findByAccountIdAndPassword(accountId, password);
        return accounts.size() == 1;
    }

}
