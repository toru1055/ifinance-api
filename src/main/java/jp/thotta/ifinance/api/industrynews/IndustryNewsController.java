package jp.thotta.ifinance.api.industrynews;

import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.dao.NewsManager;
import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thotta on 2016/05/11.
 */
@RestController
public class IndustryNewsController {
    MasterDataManager<Industry> industryManager
            = new MasterDataManager<Industry>(Industry.class);
    NewsManager newsManager = new NewsManager();

    @RequestMapping("/industry_news/industry_list")
    public List<ApiIndustry> industries() {
        List<ApiIndustry> apiIndustries = new ArrayList<ApiIndustry>();
        List<Industry> industries = industryManager.selectAll();
        for(Industry industry : industries) {
            apiIndustries.add(new ApiIndustry(industry));
        }
        return apiIndustries;
    }

    @RequestMapping("/industry_news/news_list")
    public List<ApiNews> newsList(@RequestParam(value="industries") String industries,
                                  @RequestParam(value="sort", defaultValue = "recent") String sortMode){
        List<ApiNews> apiNewsList = new ArrayList<ApiNews>();
        String[] industryStrings = industries.split("-");
        List<Integer> industryList = new ArrayList<Integer>();
        for(String industryString : industryStrings) {
            Integer industryId = Integer.parseInt(industryString);
            industryList.add(industryId);
        }
        List<News> newsList;
        if("click".equals(sortMode)) {
            newsList = newsManager.clickRanking(industryList);
        } else {
            newsList = newsManager.recentList(industryList);
        }
        for(News news : newsList) {
            apiNewsList.add(new ApiNews(news));
        }
        return apiNewsList;
    }

    @RequestMapping("/industry_news/click_news")
    public Boolean clickNews(@RequestParam(value="id") Long newsId) {
        News news = newsManager.find(newsId);
        news.incrementClicks();
        if(newsManager.update(news)) {
            return true;
        }
        return false;
    }
}
