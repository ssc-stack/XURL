package com.shorturl;

import java.util.*;

public class XUrlImpl implements XUrl {

    private Map<String,String> longToShorUrlMap=new HashMap<>();
    private Map<String,String> shortToLongUrlMap=new HashMap<>();
    private Map<String,Integer> longUrlHitCountMap=new HashMap<>();



    @Override
    public String registerNewUrl(String longUrl) {
        // TODO Auto-generated method stub
        
        if(longToShorUrlMap.containsKey(longUrl)) {
            return longToShorUrlMap.get(longUrl);
        }
        
        String randAlphaNum=RandAlphaNumGenerator.generateAlphaNum(longUrl);
        while(shortToLongUrlMap.containsKey(randAlphaNum)) {
            randAlphaNum=RandAlphaNumGenerator.generateAlphaNum(longUrl+RandAlphaNumGenerator.generateRandSalt());
        }

        String shortUrl="http://short.url/"+randAlphaNum;
        longToShorUrlMap.put(longUrl,shortUrl);
        shortToLongUrlMap.put(shortUrl,longUrl);
    
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        // TODO Auto-generated method stub

        if(shortToLongUrlMap.containsKey(shortUrl)) {
            return null;
        }

        longToShorUrlMap.put(longUrl,shortUrl);
        shortToLongUrlMap.put(shortUrl,longUrl);

        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        // TODO Auto-generated method stub

        if(shortToLongUrlMap.containsKey(shortUrl)) {
            String longUrl=shortToLongUrlMap.get(shortUrl);
            int count=longUrlHitCountMap.containsKey(longUrl)?longUrlHitCountMap.get(longUrl):0;
            longUrlHitCountMap.put(longUrl,count+1);
            return longUrl;
        }
        
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub

        if(longUrlHitCountMap.containsKey(longUrl)) {
            return longUrlHitCountMap.get(longUrl);
        }

        return 0;
    }

    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        if(longToShorUrlMap.containsKey(longUrl)) {
            String shortUrl=longToShorUrlMap.get(longUrl);
            longToShorUrlMap.remove(longUrl);
            shortToLongUrlMap.remove(shortUrl);
        }

        return longUrl;
    }
    
}