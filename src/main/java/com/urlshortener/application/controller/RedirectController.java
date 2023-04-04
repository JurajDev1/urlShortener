package com.urlshortener.application.controller;

import com.urlshortener.application.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping("/{shortUrl}")
    RedirectView redirect(@PathVariable(value = "shortUrl") String shortUrl) {
        var links = linkRepository.findByShortUrl(shortUrl);
        if (links.size() == 1) {
            var link = links.get(0);
            RedirectView redirectView = new RedirectView(link.getLongUrl(), false, false);
            redirectView.setStatusCode(HttpStatusCode.valueOf(link.getRedirectType()));
            link.setLinkUsed(link.getLinkUsed() + 1);
            linkRepository.save(link);
            return redirectView;
        } else {
            throw new RuntimeException("unknown short link");
        }
    }


}
