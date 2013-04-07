package com.vstar.lib.mock;

import java.util.ArrayList;
import java.util.List;

import com.vstar.lib.bean.BaseChannel;
import com.vstar.lib.bean.BaseImageItem;
import com.vstar.lib.bean.BaseImageList;
import com.vstar.lib.bean.BaseIndex;
import com.vstar.lib.bean.BaseIndex.Welcome;
import com.vstar.lib.bean.BaseNewsItem;
import com.vstar.lib.bean.BaseNewsList;
import com.vstar.lib.bean.BaseUser;
import com.vstar.lib.bean.More;
import com.vstar.lib.update.UpdateInfo;

public class MockData {

    public static BaseIndex index() {
        BaseIndex index = new BaseIndex();

        BaseUser baseUser = new BaseUser();
        baseUser.age = 22;
        baseUser.id = "id_43342";
        baseUser.name = "测试用户";
        baseUser.sex = "男";

        //		Logo logo = new IndexBase().new Logo();
        //		logo.name = "Logo名称";
        //		logo.pic = "";
        //		logo.wapUrl = "http://www.baidu.com";

        List<BaseChannel> navs = new ArrayList<BaseChannel>();
        for (int i = 0; i < 10; i++) {
            BaseChannel nav = new BaseChannel();
            nav.id = i;
            nav.pid = 100 + i;
            nav.name = "频道_" + i;
            nav.url = "Url_频道_" + i;
            navs.add(nav);
        }

        // Ads ads = new Index().new Ads();
        // ads.foot=new Index().new Ads().new AdInfo();
        // ads.foot.
        // ads.head=new Index().new Ads().new AdInfo();
        Welcome welcome = new BaseIndex().new Welcome();
        welcome.flag = true;
        welcome.pic = "http://avatar.csdn.net/3/B/3/1_dong3560.jpg";
        UpdateInfo updata = new UpdateInfo();
        updata.apkName = "news_1.1.apk";
        updata.apkUrl = "http://gdown.baidu.com/data/wisegame/cbe0fb2ba1d1dc55/baidushurufa.apk";
        updata.flag = true;
        updata.versionCode = 10;

        index.user = baseUser;
        //		index.logo = logo;
        index.navs = navs;
        // index.ads = ads;
        index.welcome = welcome;
        index.updata = updata;
        return index;
    }

    public static BaseNewsList newsList() {

        List<BaseNewsItem> header = new ArrayList<BaseNewsItem>();
        String[] icons = new String[] {
                                       "http://photocdn.sohu.com/20101026/Img276501782.jpg",
                                       "http://www.mm2you.com/pic/allimg/c1mmy/201108/106/10.jpg",
                                       "http://www.mm2you.com/pic/allimg/c1mmy/201108/106/11.jpg",
                                       "http://www.mm2you.com/pic/allimg/c110418/1303124H0K3F-1U95.jpg",
                                       "http://www.mm2you.com/pic/allimg/c110418/1303124K059610-149E6.jpg" };
        for (int i = 0; i < 5; i++) {
            BaseNewsItem item = new BaseNewsItem();
            item.icon = icons[i];
            item.title = "Header_title_" + i;
            item.url = "";
            header.add(item);
        }

        More more = new More();
        more.flag = true;
        more.url = "http://baidu.com.cc";

        List<BaseNewsItem> list = new ArrayList<BaseNewsItem>();
        for (int i = 0; i < 20; i++) {
            BaseNewsItem item = new BaseNewsItem();
            item.title = "content_list_item_title_" + i;
            item.url = "content_list_item_title_" + i;
            item.date = "时间_" + i;
            list.add(item);
        }

        BaseNewsList newsList = new BaseNewsList();
        newsList.flag = true;
        newsList.header = header;
        newsList.list = list;
        newsList.more = more;
        return newsList;
    }

    public static BaseImageList imageList() {
        More more = new More();
        more.flag = true;
        more.url = "http://baidu.com.cc";

        List<BaseImageItem> list = new ArrayList<BaseImageItem>();
        for (int i = 0; i < 50; i++) {
            BaseImageItem item = new BaseImageItem();
            item.title = "content_list_item_title_" + i;
            item.url = "content_list_item_title_" + i;
            list.add(item);
        }

        BaseImageList imageList = new BaseImageList();
        imageList.flag = true;
        imageList.images = list;
        imageList.more = more;
        return imageList;
    }
}
