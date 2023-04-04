package com.urlshortener.application.repository;

import com.urlshortener.application.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository  extends JpaRepository<Link, Long> {

    List<Link> findByAccountId(String accountId);

    List<Link> findByShortUrl(String shortUrl);

}
