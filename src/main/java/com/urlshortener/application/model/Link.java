package com.urlshortener.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "links")
public class Link {

    private long id;
    @Column(name = "longUrl", nullable = false)
    public String longUrl;

    @Column(name = "shortUrl", nullable = false)
    public String shortUrl;
    @Column(name = "redirectType", nullable = false)
    public int redirectType;
    @Column(name = "linkUsed", nullable = false)
    public int linkUsed = 0;

    @Column(name = "accountId", nullable = false)
    public String accountId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getLinkUsed() {
        return linkUsed;
    }

    public void setLinkUsed(int linkUsed) {
        this.linkUsed = linkUsed;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
