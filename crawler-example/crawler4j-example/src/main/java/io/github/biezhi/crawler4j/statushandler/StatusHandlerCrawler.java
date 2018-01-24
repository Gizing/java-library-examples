package io.github.biezhi.crawler4j.statushandler;

import java.util.regex.Pattern;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar
 */
public class StatusHandlerCrawler extends WebCrawler {
    private static final Logger logger = LoggerFactory.getLogger(StatusHandlerCrawler.class);

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * You should implement this function to specify whether
     * the given url should be crawled or not (based on your
     * crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        // Do nothing
    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {

        if (statusCode != HttpStatus.SC_OK) {

            if (statusCode == HttpStatus.SC_NOT_FOUND) {
                logger.warn("Broken link: {}, this link was found in page: {}", webUrl.getURL(),
                        webUrl.getParentUrl());
            } else {
                logger.warn("Non success status for link: {} status code: {}, description: ",
                        webUrl.getURL(), statusCode, statusDescription);
            }
        }
    }
}