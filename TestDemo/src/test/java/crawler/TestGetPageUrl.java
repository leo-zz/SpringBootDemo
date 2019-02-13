//package crawler;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestGetPageUrl implements PageProcessor {
//
//    static List<String> strs=new ArrayList<>();
//    static int i=0;
//    private static String[] sites={"http://91yingyuan.sex1524.com:9202/crsp.php","http://91yingyuan.sex1524.com:9202/dgsp.php","http://91yingyuan.sex1524.com:9202/znsp.php","http://91yingyuan.sex1524.com:9202/wysp.php"};
//    private static String[] site1s={"crlist","dglist","znlist","wylist"};
//
//    private Site site=Site.me().setDomain("91yingyuan.sex1524.com:9202").setSleepTime(100);
//
//    public static void main(String[] args) {
//        Spider spider = Spider.create(new TestGetPageUrl());
//        for (int j=0;j<4;j++){
//            spider.addUrl(sites[j]).run();
//            i++;
//        }
//        System.out.println("总共有记录"+strs.size()+"条");
//        Spider spider1 = Spider.create(new TestGetVideoUrl());
//
//        for (String str:strs
//             ) {
//            spider1.addUrl(str).run();
//        }
//        System.out.println("总共有记录"+TestGetVideoUrl.strings.size()+"条");
//        for (String str:TestGetVideoUrl.strings
//             ) {
//            System.out.println(str);
//        }
//
//    }
//
//    @Override
//    public void process(Page page) {
//        List<String> all = page.getHtml().$("div." + site1s[i]).links().regex(".*play.php.*").all();
//        strs.addAll(all);
//        for (String str:all
//             ) {
//            System.out.println(str);
//        }
//        System.out.println("共有记录"+all.size()+"条");
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//}
