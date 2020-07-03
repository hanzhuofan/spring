package com.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanzhuofan
 * @date 2020/7/3 19:
 */
public class TestPaly {
    public static void main(String[] args) {
        SearchQueryData searchQueryData = new TestPaly().decodeQuery("1:2:3:4:5:6");
        System.out.println(searchQueryData);
    }

    interface Callback {
        void call(SearchQueryData queryData, String[] split);
    }

    static Map<Integer, Callback> mapFunction = new HashMap<>();

    private void getMapFunction() {
        mapFunction.put(0, (queryData, split) -> {
        });
        mapFunction.put(1, this::split1);
        mapFunction.put(2, this::split2);
        mapFunction.put(3, this::split3);
    }

    private void split1(SearchQueryData queryData, String[] split) {
        queryData.setTextSearch(split[0]);
    }

    private void split2(SearchQueryData queryData, String[] split) {
        split1(queryData, split);
        queryData.setSort(split[1]);
    }

    private void split3(SearchQueryData queryData, String[] split) {
        split2(queryData, split);
        List<SearchQueryTermData> terms = new ArrayList<>();
        for (int i = 2; (i + 1) < split.length; i += 2) {
            SearchQueryTermData termData = new SearchQueryTermData();
            termData.setKey(split[i]);
            try {
                termData.setValue(URLDecoder.decode(split[i + 1], "utf-8"));
            } catch (UnsupportedEncodingException e) {
                // the exception should be handled
            }
            terms.add(termData);
        }
        queryData.setQueryTerms(terms);
    }

    private SearchQueryData decodeQuery(String query) {
        SearchQueryData queryData = new SearchQueryData();
        if (query != null) {
            String[] split = query.split(":");
            getMapFunction();
            mapFunction.get(split.length > 2 ? 3 : split.length).call(queryData, split);
        }
        return queryData;
    }
}

class SearchQueryData {
    String textSearch;
    String sort;
    List<SearchQueryTermData> queryTerms;

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setQueryTerms(List<SearchQueryTermData> queryTerms) {
        this.queryTerms = queryTerms;
    }

    @Override
    public String toString() {
        return "{" +
                "textSearch='" + textSearch + '\'' +
                ", sort='" + sort + '\'' +
                ", queryTerms=" + queryTerms +
                '}';
    }
}

class SearchQueryTermData {
    String key;
    String value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
