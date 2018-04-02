package com.liwenpeng.topnews.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.constant.UrlBaseConstant;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * liwenpeng
 * 2018/4/1 20:51
 */
public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";

    private static NewsBean newsBean;
    private static OkHttpClient client;

    public static NewsBean  getOkHttpResponse(final Context context, String url, final int length){
        if (client == null){
            client = new OkHttpClient();
        }
        Request request =new Request.Builder().url(UrlBaseConstant.TOP_URL).build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d(TAG,"body :"+body);
            //Gson解析json数据
            Gson gson = new Gson();
            newsBean = gson.fromJson(body, NewsBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBean;
    }

    //本地json
    public static NewsBean  getLocalResponse(){
//        if (client == null){
//            client = new OkHttpClient();
//        }
//        Request request =new Request.Builder().url(UrlBaseConstant.TOP_URL).build();
//        try {
//            Response response = client.newCall(request).execute();
//            String body = response.body().string();
//            Log.d(TAG,"body :"+body);
            //Gson解析json数据
        String body = "{\n" +
                "\n" +
                "    \"reason\": \"成功的返回\",\n" +
                "    \"result\": {\n" +
                "        \"stat\": \"1\",\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"uniquekey\": \"acdcca8ada9a1ef075b3670ec80be492\",\n" +
                "                \"title\": \"特朗普挨国人一记重拳 中国今起对128项美国商品加征关税\",\n" +
                "                \"date\": \"2018-04-02 10:42\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"东方头条\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402104205712.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402_0202e0bab5d36fcae93ce6000663af19_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402_70bc5fd587c60a3991bc28c1dc8ac318_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402_aa96ff2bfeaa305933cad0bd81cb0518_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"aba1c7a10ada9b041ab568e7a6de6035\",\n" +
                "                \"title\": \"住建部：减少专业承包资质等级，政府投资并非单纯最低价中标\",\n" +
                "                \"date\": \"2018-04-02 10:35\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"澎湃新闻网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402103503541.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://03.imgmini.eastday.com/mobile/20180402/20180402103503_37e6a47aca35383d286ca2ec87ada5a7_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"66bf6ac8cb3fcbab97d0a410577e5da7\",\n" +
                "                \"title\": \"“新时代中国乡村振兴战略智库论坛”在京举行\",\n" +
                "                \"date\": \"2018-04-02 10:27\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"中国新闻网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402102737728.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402102737_f015a0867b54d6eb73f66c9b32bd5358_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"790122f524b8dc1ec6e6b3c1dda543e6\",\n" +
                "                \"title\": \"勘定陆地边界 越南柬埔寨今年定了个“小目标”\",\n" +
                "                \"date\": \"2018-04-02 10:21\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"海外网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402102117650.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402102117_f7ac99e0633ab34f23b16e9bb5efb69c_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"5edcb5f9bece918fe1a6af81d3bfa50c\",\n" +
                "                \"title\": \"天津南开区北方五金机电商城发生火灾，造成一人死亡\",\n" +
                "                \"date\": \"2018-04-02 10:16\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"平安南开\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402101643203.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402101643_c92b0c60555ef66d9e3f7d0b4be54f8e_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"2fbd2c21e61076acbca7db114e2adbc7\",\n" +
                "                \"title\": \"“健身不练腿，迟早就要废”，这五个动作打造强壮下肢\",\n" +
                "                \"date\": \"2018-04-02 10:10\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"悦动减脂营\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402101016251.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402101016_cdd966e4c8eec1d6eb8b45726b8d2a56_4_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402101016_cdd966e4c8eec1d6eb8b45726b8d2a56_3_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402101016_cdd966e4c8eec1d6eb8b45726b8d2a56_5_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"a8ecd978c83d0ee2875b16ff5051c950\",\n" +
                "                \"title\": \"上海为诺贝尔奖得主维特里希等6人颁发外国人永久居留身份证：这些人能申请中国永居\",\n" +
                "                \"date\": \"2018-04-02 10:08\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"解放网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402100830237.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402100830_cd6d27c78a19cbcc80b45fcd461118dc_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"a21737474ecae6544120a592bcbcd4a5\",\n" +
                "                \"title\": \"以色列实力究竟有多强? 除核武器之外, 这一独门利器不容小觑\",\n" +
                "                \"date\": \"2018-04-02 10:06\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"当兵女神\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402100652130.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402_14799a9827c52d6a6b78618cb734667c_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402_bfa2d8ff1eabd2a64c8cb93e04c0c2f2_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402_d2e6520b6ea50b0c89e105a24769add6_cover_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"0fb2451d2365db5aeded5551b060c2bf\",\n" +
                "                \"title\": \"美军再次空袭叙利亚, 俄军基地被毁, 冲突信号已打响!\",\n" +
                "                \"date\": \"2018-04-02 10:02\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"当兵女神\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402100225714.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402_c99ae808ff69848d5516075a2e147b79_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402_c0724876d1e6a024661182410b39bbda_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402_6e4a57904a3412971cdf0901ef9bfbb5_cover_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"c6dd260157f05c524fbc87ed2bb45f5c\",\n" +
                "                \"title\": \"自称“店小二”是干部公仆意识的自觉回归\",\n" +
                "                \"date\": \"2018-04-02 10:00\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"海外网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402100009627.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402100009_f613b5f03d6ebfdbfa9ec475d3eae3c9_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"922647b3638fcd5c7904e67398e82f9d\",\n" +
                "                \"title\": \"人民日报今年第3次刊发“宣言”署名文章，这个宣言是谁？\",\n" +
                "                \"date\": \"2018-04-02 09:44\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"中国搜索\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402094455899.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402094455_ce051c55ce95bd1b35eef2787bb2ddd4_4_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402094455_ce051c55ce95bd1b35eef2787bb2ddd4_6_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402094455_ce051c55ce95bd1b35eef2787bb2ddd4_5_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"e5695c90e7a34202cfc5f7384ec2e837\",\n" +
                "                \"title\": \"运输航空公司2019年机队规划备用指标数量公示\",\n" +
                "                \"date\": \"2018-04-02 09:33\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"中国民航局\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402093340749.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402093340_c6f5f3a931d2454eb5f4e3437efb8d9f_2_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402093340_c6f5f3a931d2454eb5f4e3437efb8d9f_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"109386281568e2c74e01e7993c7e498f\",\n" +
                "                \"title\": \"高雄夜市物换星移 商品同构型高是最大杀手\",\n" +
                "                \"date\": \"2018-04-02 09:30\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"华夏经纬网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402093014975.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://07.imgmini.eastday.com/mobile/20180402/20180402093014_96d1b0ac2f01fc5271ef049c3a3a396c_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://07.imgmini.eastday.com/mobile/20180402/20180402093014_96d1b0ac2f01fc5271ef049c3a3a396c_2_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"062a921e0f559ebc7963365cf9c8617e\",\n" +
                "                \"title\": \"人不胖，但肚子大，这种情况要注意了！\",\n" +
                "                \"date\": \"2018-04-02 09:10\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"健康与养生\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402091047641.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402091047_783e75a959bee56dbec31577750c644e_3_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402091047_783e75a959bee56dbec31577750c644e_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402091047_783e75a959bee56dbec31577750c644e_4_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"8dec2d1fb68e908a2191552aa0450788\",\n" +
                "                \"title\": \"发挥文化在国家治理体系现代化中的作用\",\n" +
                "                \"date\": \"2018-04-02 09:01\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"吉林日报\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402090105408.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402090105_a3748bb0d34108a19fa6c2fb362dba78_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"f65fdf2e05e08b8b5c27b2963f495807\",\n" +
                "                \"title\": \"程永如参赞应邀在科威特澳大利亚大学介绍“一带一路”\",\n" +
                "                \"date\": \"2018-04-02 08:47\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"驻科威特使馆经商处\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402084712803.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402084712_05ded97bb5a6eaa4071011615b04a222_2_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402084712_05ded97bb5a6eaa4071011615b04a222_3_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402084712_05ded97bb5a6eaa4071011615b04a222_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"e2259cbe1311c4ccd7c2de3c214cab42\",\n" +
                "                \"title\": \"环境保护税首征 \\\"美丽广西\\\"环境保护进入\\\"税时代\\\"\",\n" +
                "                \"date\": \"2018-04-02 08:43\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"广西新闻网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402084300990.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://00.imgmini.eastday.com/mobile/20180402/20180402084300_2a5feb51c4c109bfada8ad921b3a314b_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"beaf1d4b47ac52d2710b4267a5d40b66\",\n" +
                "                \"title\": \"广东省阳春市政协为生活垃圾处理建言献策\",\n" +
                "                \"date\": \"2018-04-02 08:41\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"人民政协网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402084158725.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://03.imgmini.eastday.com/mobile/20180402/20180402084158_4c3e3a678a5c17afbc3ec1a510a49b19_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"67c318008b2dadcaf5d9cc84175324da\",\n" +
                "                \"title\": \"俄罗斯再次强硬出手，英国黔驴技穷被怼无语！\",\n" +
                "                \"date\": \"2018-04-02 08:41\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"第一军情\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402084102740.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402_80fd5a7a8f5c7911026fef5ca1019679_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402_3125030ab118ede95e62df4f890eeeaf_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402_dc780fbb025680120c1acfcc64fd6324_cover_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"a125d506f520a7528bf5e1d76a3755cd\",\n" +
                "                \"title\": \"这4个动作, 健身房里会经常用到\",\n" +
                "                \"date\": \"2018-04-02 08:35\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"威林爱健身\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402083554370.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://00.imgmini.eastday.com/mobile/20180402/20180402_da247a5a058d1585625453a90c498031_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://00.imgmini.eastday.com/mobile/20180402/20180402_4887e37c4beacc55925f822244ae753c_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://00.imgmini.eastday.com/mobile/20180402/20180402_1d5ac75f87d7a7bb730612e9b9abc06c_cover_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"2d8cfd27bfc5990ae2e760f6e12c4e55\",\n" +
                "                \"title\": \"“小确幸”梦碎“闷世代”来临\",\n" +
                "                \"date\": \"2018-04-02 08:33\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"东南网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402083354677.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402083354_8198f1c174ff54f7f6b38cdb614d0567_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"9b34bf4c63d95efa89eaa15ab13c87d2\",\n" +
                "                \"title\": \"马云“彻底发飙”, 你不仁休要怪我不义, 支持干掉滴滴!\",\n" +
                "                \"date\": \"2018-04-02 08:29\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"科技圈子\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402082901122.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402082901_b7d9b09986879300839e7c166d150b98_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402082901_b7d9b09986879300839e7c166d150b98_2_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://05.imgmini.eastday.com/mobile/20180402/20180402082901_b7d9b09986879300839e7c166d150b98_3_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"8d3e8fff9000ab22c38b4c9b6c3e4c75\",\n" +
                "                \"title\": \"俄叙心理战取得大胜，数千反叛武装主动投降求生存\",\n" +
                "                \"date\": \"2018-04-02 08:22\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"军事亮评\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402082228278.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402_c48033eb66ff35fa924e4fc1a862229d_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402_9a9719e003d78da042a7a70593b740bf_cover_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://02.imgmini.eastday.com/mobile/20180402/20180402_51fd8fe3dd8b1f37298b3ad97d6d59ce_cover_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"8dfa77e66d32f7c24bc9af686e9cbfda\",\n" +
                "                \"title\": \"澳芬克斯黑帮飞车队归来 称会远离犯罪\",\n" +
                "                \"date\": \"2018-04-02 08:20\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"环球网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402082034485.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402082034_d17eccea9109e738333a944afa330dc3_3_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402082034_d17eccea9109e738333a944afa330dc3_4_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://06.imgmini.eastday.com/mobile/20180402/20180402082034_d17eccea9109e738333a944afa330dc3_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"d8d5217c0ddb3960ef983efd61418165\",\n" +
                "                \"title\": \"特朗普复活节周末遇烦心事：名下俱乐部遭泼红漆\",\n" +
                "                \"date\": \"2018-04-02 08:15\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"海外网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402081510003.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402081510_1c5d479db66d24cc36c2b43958a959b1_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402081510_1c5d479db66d24cc36c2b43958a959b1_3_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s03\": \"http://09.imgmini.eastday.com/mobile/20180402/20180402081510_1c5d479db66d24cc36c2b43958a959b1_2_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"992882160cd926aa842daad029499efb\",\n" +
                "                \"title\": \"三明市三元区政协首次向委员寄送年度履职成绩单\",\n" +
                "                \"date\": \"2018-04-02 08:11\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"人民政协网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402081136188.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://01.imgmini.eastday.com/mobile/20180402/20180402081136_8c2b2c2c89fc422e78bcd182a1cda3d5_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"9b4140f9d0c2e345f7e72c468fbf8a93\",\n" +
                "                \"title\": \"财政部：设立国家融资担保基金 助推中小微企业成长壮大\",\n" +
                "                \"date\": \"2018-04-02 08:09\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"金融时报\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402080914164.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://08.imgmini.eastday.com/mobile/20180402/20180402080914_7788e3c2d3804afc4761ef5d360e44e4_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://08.imgmini.eastday.com/mobile/20180402/20180402080914_7788e3c2d3804afc4761ef5d360e44e4_2_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"32c6f31cca8b23beb1fd92573b332664\",\n" +
                "                \"title\": \"鲍永能：庄严祭祖凝心 红色文化铸魂\",\n" +
                "                \"date\": \"2018-04-02 08:04\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"海外网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402080443629.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://03.imgmini.eastday.com/mobile/20180402/20180402080443_7a4f7b2f0d906820c1051e0d3de1c532_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"331dd764edba2bb0009d13844e786d77\",\n" +
                "                \"title\": \"孙晓梅:十份议案建议助力反家庭暴力立法\",\n" +
                "                \"date\": \"2018-04-02 08:01\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"正义网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402080149473.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://07.imgmini.eastday.com/mobile/20180402/20180402080149_71edee43a62ac1115acc5e8bbcbbb913_1_mwpm_03200403.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"uniquekey\": \"e3bb0c7b70e0a62f63957cddd0833dab\",\n" +
                "                \"title\": \"草原男儿白照广：不牧牛羊牧“星辰”\",\n" +
                "                \"date\": \"2018-04-02 07:59\",\n" +
                "                \"category\": \"头条\",\n" +
                "                \"author_name\": \"央视网\",\n" +
                "                \"url\": \"http://mini.eastday.com/mobile/180402075931194.html\",\n" +
                "                \"thumbnail_pic_s\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402075931_2f7db8f5b5759ec7b42664e42a8bd763_1_mwpm_03200403.jpg\",\n" +
                "                \"thumbnail_pic_s02\": \"http://04.imgmini.eastday.com/mobile/20180402/20180402075931_2f7db8f5b5759ec7b42664e42a8bd763_2_mwpm_03200403.jpg\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"error_code\": 0\n" +
                "\n" +
                "}\n";
            Gson gson = new Gson();
            newsBean = gson.fromJson(body, NewsBean.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return newsBean;
    }
}
