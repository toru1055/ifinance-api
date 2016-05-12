package jp.thotta.ifinance.api.industrynews;

import jp.thotta.ifinance.common.entity.News;

import java.util.Date;

/**
 * Created by thotta on 2016/05/11.
 */
public class ApiNews {
    Long id; //primary key
    String url; //not null
    String title; //not null
    String description;
    String subscriptionName;
    String industryName;
    Date pubDate;

    public ApiNews(News news) {
        this.id = news.getId();
        this.url = news.getUrl();
        this.title = news.getTitle();
        this.description = news.getDescription();
        this.subscriptionName = news.getSubscription().getName();
        this.industryName = news.getSubscription().getFixedIndustry().getName();
        this.pubDate = news.getAnnouncedDate();
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public Date getPubDate() {
        return pubDate;
    }
}
