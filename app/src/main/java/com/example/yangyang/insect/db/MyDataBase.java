package com.example.yangyang.insect.db;

import android.util.Log;

import com.example.yangyang.insect.entity.Insect;
import com.example.yangyang.insect.entity.MyComment;
import com.example.yangyang.insect.entity.Passage;
import com.example.yangyang.insect.entity.Video;

import org.litepal.LitePal;

import java.util.ArrayList;

/**
 * Created by yangyang on 2019/3/5.
 * 项目名：insect
 * 包名 ： com.example.yangyang.insect.db
 * 作用：
 */
public class MyDataBase {
    private static String [] head_uri  = {
            "http://mmbiz.qpic.cn/mmbiz_png/Ix0yvq55xgaQdQLfxRwENPxaXZXIGqZBjhCXf1mH4pbpmiaaIcEy0w2xo2mmuxcrgYC4ibhXFTIoRh1kKTVSTLEQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgb8LCj8QGChUE8ZNgMuokiarrGuIxOMGYSPkFicaDMk6jicagX85H2AcjRAyXltTliaJZibjibkWGN7my6g/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgb2Bqicp0E6jB7cqZ7b9t3RkFAFLe6knMm8ZLrJpd2E7lGxBib9ibXbMpof4ACwOJwpVVkRkVOcpMHJw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgYjUjAHbJVWv1IYVdYbh2zlz612IfL5S1YfxpDo2xe4ia4AJibo0wN1RvQl1qpxx0G1Et2siay8icu6cw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_png/Ix0yvq55xgZCdHoVeSTHmJK4f9K30blUxXaBTyVKQ8mia9E9z8r6QXNzcftkYLM4e3lsN0XEX7MYyrjhM9rJmXg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgZs8gArwt9FIzpgQcO3QdxE9revcibAfsjo8ibiaREZomP21k0eMtplyxLeuRNoPCoHtfVVeKZibfCZkA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_png/7ldgTYw4nib5icMiaZz2C61rFkcD58GrY1dV8B7WfbWO3ax6IVgCjbegblNUiaiaGl3bT5qlCvZ1SaQjxr0icTwvWpOA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_png/Ix0yvq55xgakoeQrt8BoQWgI8PUVI9IxFLH0PaQE6HHtaaHpCrqaEjTWkTYpH7mibUpJmIoMyneynP12iapTSz1Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgb7ejXfEIpZGm0YxFealPjjIMuNsibc7GbNfGCJIJFpRPJg9Rq6O83mCfkyNXavUia99FhjVMSBveFQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgZOia2OJzGDmrVia5rwNvj8TmqSr3Ph2jbmmVAdkicbvqhhLia54ehwxIDeVeWlQSibGFcaw1DC2jDLFWw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",
            "http://mmbiz.qpic.cn/mmbiz_jpg/Ix0yvq55xgYDribxAMnZeibY5Zxw9fvrOwibWicBmPZ9zanxj8pZkiczYibeibNNBJcuGyb4du2GUh4BVKcygN0hjuwQQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1",

    };

    private static String title [] = {
            "【虫研捷报】第一个由中国人发表的棒角甲问世",
            "【虫趣点滴】要做到眼观六路，即使，我只是一只苍蝇",
            "【虫谱大观】吾虽小，却也是恶魔！——阎甲科昆虫介绍",
            "【虫谱大观】传说与现实—鬼脸天蛾",
            "【虫研捷报】我国地鳖蠊科昆虫添7新成员",
            "【虫趣点滴】怎样区别“蜣螂”和“粪金龟”？",
            "夏夜看萤火虫，不是只能去深山老林丨科普",
            "【全新推出】奇翅目-缅甸琥珀中的古生物高浮雕系列军牌",
            "【肖蛸在户】还原古蛛（Archaeidae）的真实身份",
            "【虫心未泯】鸡年话“鸡”虫",
            "【虫趣点滴】我要这破灯有何用",
    };

    private static String passage_uri [] = {
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650838230&idx=1&sn=faba510024fceff42092c5dbf3135974",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650838058&idx=1&sn=589ce155c954cbfe28e1c632cf249a72",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837997&idx=1&sn=eceb3c0370309935123a8add0ca6ddd8",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837998&idx=1&sn=89d1bc074409a4897c0e2eb564d81360",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837891&idx=1&sn=130b13d92a467e8355e26d520cda8669",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837880&idx=1&sn=e064002d692f950af9fa838d1f2bd847",
            "https://mp.weixin.qq.com/s?__biz=MzI5MTIwMDg2OA==&mid=2651514844&idx=1&sn=b46280548f28c17e9008f950ce6998b3",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837451&idx=1&sn=3858f6e0873a7434032e961b6d5e6e99",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837519&idx=1&sn=440a7e6c83cf62bda690dcd1e6b374e1",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837659&idx=1&sn=c1fe7e6736b45826605fb6e3a032ee04",
            "https://mp.weixin.qq.com/s?__biz=MjM5NDAzODcwNg==&mid=2650837676&idx=1&sn=b46f156c194ea7cbdca6920cd313cbfc",

    };
    public static void formPassageToDB(){
        for (int i = 0;i<passage_uri.length;i++){
            Passage passage = new Passage();
            passage.setHead_uri(head_uri[i]);
            passage.setTitle(title[i]);
            passage.setPassage_uri(passage_uri[i]);
            passage.save();
        }

    }
    // video
    private static String [] introduce = {
            "昆虫界的咒术大师，可以控制蟑螂成为傀儡，这虫子太恐怖了",
            "专吃昆虫的捕蝇草对芥末会有什么反应？小伙实验，2秒后出人意料",
            "世界上10种蛰人最疼的昆虫，被第一名蛰上一口，疼痛感很难忘记！",
            "不喜欢捕捉昆虫的蜘蛛，只喜欢踩点钓鱼，网友：不务正业",
            "德爷堪比贝爷吃昆虫：质感浓厚，又生吃蝎子：哇，真多汁！",
    };
    private static String [] video_uri = {
            "https://vd2.bdstatic.com/mda-iicqnzvdjbrqh7m7/sc/mda-iicqnzvdjbrqh7m7.mp4?auth_key=1552018605-0-0-4d7fc15db4de6bcdc5b5077e657cb4e3&bcevod_channel=searchbox_feed&pd=bjh&abtest=all",
            "https://vd4.bdstatic.com/mda-imkxjdefnbvssunu/sc/mda-imkxjdefnbvssunu.mp4?auth_key=1552018805-0-0-9889f592dcad90de1387e3a5c566b63b&bcevod_channel=searchbox_feed&pd=bjh&abtest=all",
            "https://vd2.bdstatic.com/mda-im2na8f11hb3i921/sc/mda-im2na8f11hb3i921.mp4?auth_key=1552018898-0-0-4a3bad0a00a5fc432d6c752ff0385560&bcevod_channel=searchbox_feed&pd=bjh&abtest=all",
            "https://vd3.bdstatic.com/mda-ijbjtauy5tz10jd9/sc/mda-ijbjtauy5tz10jd9.mp4?auth_key=1552018971-0-0-3591242682e9db87893c3e22e27564e4&bcevod_channel=searchbox_feed&pd=bjh&abtest=all",
            "https://vd3.bdstatic.com/mda-ii5kb4gbzzhg6vfx/sc/mda-ii5kb4gbzzhg6vfx.mp4?auth_key=1552019122-0-0-b08d401a90bff8d61c84ffe1bb440a9c&bcevod_channel=searchbox_feed&pd=bjh&abtest=all",
    };
    private static String [] load_uri = {
            "https://i03piccdn.sogoucdn.com/a4ddb7c8548812cf",
            "https://i04piccdn.sogoucdn.com/0857cde39a2dbc5c",
            "https://i04picsos.sogoucdn.com/9bd5ba23acaa7bd2",
            "https://img04.sogoucdn.com/app/a/100520093/ac75323d6b6de243-dde843d37055636a-df1f914668cdc8e140256ea4574d37ba.jpg",
            "https://img03.sogoucdn.com/app/a/100520093/1e764170ca558da7-4c0b8ccbd9b3f301-d680a16fdc9085fc533b22b307711009.jpg",
    };
    private static String [] writer_head_uri = {
            "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/5675e6b48be89b8be89b8b736b79143f?t=1412752819",
            "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/6c14e5b7a8e59e8be89d88e89d88654f?t=1501160214",
            "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/39537a696c61726b79a206",
            "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/3fbbe5a4a9e4b88de8939de6b5b7879f?t=1548220947",
            "https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/2e78e588abe59381e98687e88c975d3a",
    };
    private static String [] writer_name = {
            "洋蛋蛋sky",
            "蝈蝈老贼",
            "zilarky",
            "天不蓝海",
            "别品醇茗",
    };
    // MyComment
    private static String [] comment_head_uri = {
            "https://tb1.bdstatic.com/tb/yangchaoyue.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1408698273,2521972903&fm=27&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3377302992,3361149372&fm=27&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3533755074,2479010611&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2467153489,966901276&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2515071376,1278574704&fm=27&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3393402233,2133215865&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3638963425,188168073&fm=27&gp=0.jpg",
            "https://avatar.csdn.net/3/1/B/3_u012552052.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=153106697,274998536&fm=27&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3377302992,3361149372&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3638963425,188168073&fm=27&gp=0.jpg",
    };
    private static String [] comment_name = {
            "明明如越","joeygallo","  呆萌的呆萌"," 阿阮是我老婆"," 青莲剑歌",
            "越哥的小护卫","巡山小胖", "我善良的", "大海蟑螂", "nice小小狼仔",
            "宁静致远", "我要胖胖胖", "周树人与猹", "一次就好", "开山妖",

    };
    private static String [] comment_content = {
            "我的老天爷爷啊",
            "66666",
            "隔着屏幕都不敢碰他",
            "厉害",
            "哈哈哈",
            "好恐怖我都不敢摸手机了",
            "好吓人",
            "⊂ヽ\n" +
                    "　 ＼＼ ∧＿∧\n" +
                    "　　 ＼( ˘ω˘ )　\n" +
                    "　　　 >　⌒ヽ\n" +
                    "　　　/ 　 へ＼\n" +
                    "　　 /　　/　＼＼\n" +
                    "　　 ﾚ　ノ　　 ヽ_つ\n" +
                    "　　/　/\n" +
                    "　 /　/|\n" +
                    "　(　(ヽ\n" +
                    "　|　|、＼\n" +
                    "　| 丿 ＼ ⌒)\n" +
                    "　| |　　) /" +
                    "(_／\n"+ "ノ )　　Lﾉ" + "上吧！菊花猫(≥∇≤)猫哥镇楼",

            "别碰伦我小时候被毒死过",
            "真香警告！",
            "对不起我大晚上的有点害怕",
            "卧槽",
            "艾玛",
            "皮都看麻了",
            "牛批",
    };

    public static void formVideoToDB(){
        // 创建comment的arraylist

//        ArrayList<MyComment> myComments = new ArrayList<>();
//        MyComment myComment = new MyComment();
//        myComment.setComment_name("111");
//        myComment.setComment("222");
//        myComment.setComment_head_uri("http://tb.himg.baidu.com/sys/portrait/item/c8ba6a6f657967616c6c6f337a");
//        myComments.add(myComment);
        for (int k = 0; k < comment_content.length;k++){
            MyComment myComment = new MyComment();
            myComment.setNum(k);
            myComment.setComment_head_uri(comment_head_uri[k]);
            myComment.setComment_name(comment_name[k]);
            myComment.setComment(comment_content[k]);
            myComment.save();
        }

        for (int i = 0; i<video_uri.length; i++){

            Video video = new Video();
            video.setIntroduce(introduce[i]);
            video.setVideo_uri(video_uri[i]);
            video.setLoading_uri(load_uri[i]);
            video.setHead_uri(writer_head_uri[i]);
            video.setName(writer_name[i]);
            video.save();

        }
    }
    // insect
    static String [] insect_name = {
            "膜翅目",
            "双翅目",
            "鳞翅目",
            "脉翅目",
            "鞘翅目",
            "半翅目",
            "膜翅目",
            "直翅目",
            "螳螂目",
            "蜻蜓目",
    };

    static String [] insect_uri = {
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1179605375,1641878657&fm=27&gp=0.jpg",
            "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3427923409,2507460755&fm=58&bpow=350&bpoh=270",
            "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=c59f1fc9d7ca7bcb7d7bc02986320c5e/4610b912c8fcc3ce746cef399245d688d43f200f.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3806883529,4207093248&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2408431100,2208195&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2930741493,2439496250&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4245132319,2821264153&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2321065076,4130635346&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=66704997,4275292751&fm=200&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=956242821,1271871417&fm=26&gp=0.jpg",
    };

    public static void formInsectToDB(){
        for (int i = 0;i<insect_name.length;i++){
            Insect insect = new Insect();
            insect.setName(insect_name[i]);
            insect.setHead_uri(insect_uri[i]);
            insect.save();
        }
    }




















}
