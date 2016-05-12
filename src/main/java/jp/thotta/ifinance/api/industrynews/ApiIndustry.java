package jp.thotta.ifinance.api.industrynews;

import jp.thotta.ifinance.common.entity.Industry;

/**
 * Created by thotta on 2016/05/11.
 */
public class ApiIndustry {
    Integer id;
    String name;

    public ApiIndustry(Industry industry) {
        this.id = industry.getId();
        this.name = industry.getName();
    }

    public ApiIndustry(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
