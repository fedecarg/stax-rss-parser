package com.fedecarg.rss.reader;

import sun.security.provider.MD5;

import com.fedecarg.rss.model.Feed;
import com.fedecarg.rss.model.FeedMessage;

public class ReadTest {

    public static void main(String[] args) {

        RSSFeedParser parser = new RSSFeedParser("http://www.vogella.de/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message.getGuid());
        }
    }
}
