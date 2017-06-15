package org;

import org.zhaojun.spider.controller.SpiderController;


public class App {

    public static void main(String[] args) {
        /*启动爬虫*/
//        SpiderFrame frame = new SpiderFrame();
//        frame.go();
        SpiderController yySpider = new SpiderController();
        yySpider.start();

    }
}
