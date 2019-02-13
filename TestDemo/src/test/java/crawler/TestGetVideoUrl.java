//package crawler;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//import us.codecraft.webmagic.selector.Selectable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestGetVideoUrl implements PageProcessor {
//
//    private Site site=Site.me().setDomain("91yingyuan.sex1524.com:9202").setSleepTime(10);
//    private static String url="http://91yingyuan.sex1524.com:9202/play.php?id=130";
//    public static ArrayList<String> strings = new ArrayList<>();
//
//    public static void main(String[] args) {
//        Spider.create(new TestGetVideoUrl()).addUrl(url).run();
//    }
//
//    @Override
//    public void process(Page page) {
//        Selectable cc = page.getHtml().$("source","src");
////        Selectable cc = page.getHtml().$("div.playContent");
////        System.out.println(cc);
//        strings.add(cc.toString());
////        System.out.println(cc.toString());
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//}
