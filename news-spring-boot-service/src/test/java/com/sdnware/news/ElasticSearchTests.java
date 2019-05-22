package com.sdnware.news;

import bootstrap.NewsServiceRunner;
import com.sdnware.news.api.NewsService;
import com.sdnware.news.pojo.es.NewsInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by sdnware on 18-8-24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsServiceRunner.class)
public class ElasticSearchTests {

    @Autowired
    NewsService newsService;

    @Test
    public void addNewsTest() {
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setId(UUID.randomUUID().toString());
        newsInfo.setTitle("习近平：举旗帜聚民心育新人兴文化展形象 更好完成新形势下宣传思想工作使命任务");
        newsInfo.setSubTitle("");
        newsInfo.setPublicTime("2018-08-22 23:26:34");
        newsInfo.setAuthor("新华网");
        newsInfo.setContent("新华社北京8月22日电 全国宣传思想工作会议21日至22日在北京召开。中共中央总书记、国家主席、中央军委主席习近平出席会议并发表重要讲话。他强调，完成新形势下宣传思想工作的使命任务，必须以新时代中国特色社会主义思想和党的十九大精神为指导，增强“四个意识”、坚定“四个自信”，自觉承担起举旗帜、聚民心、育新人、兴文化、展形象的使命任务，坚持正确政治方向，在基础性、战略性工作上下功夫，在关键处、要害处下功夫，在工作质量和水平上下功夫，推动宣传思想工作不断强起来，促进全体人民在理想信念、价值理念、道德观念上紧紧团结在一起，为服务党和国家事业全局作出更大贡献。");
        newsInfo.setState(1);
        newsInfo.setReadCount(1811L);
        newsService.saveNews(newsInfo);
    }

    @Test
    public void bulkSaveNewsTest() {
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setId(UUID.randomUUID().toString());
        newsInfo.setTitle("习近平为新形势下宣传思想工作划重点");
        newsInfo.setSubTitle("");
        newsInfo.setPublicTime("2018-08-24 08:46:28");
        newsInfo.setAuthor("新华网");
        newsInfo.setContent("【学习进行时】8月21日至22日，全国宣传思想工作会议召开，习近平总书记在会上发表重要讲话，对新形势下党的宣传思想工作作出重大部署，强调推动宣传思想工作不断强起来，促进全体人民在理想信念、价值理念、道德观念上紧紧团结在一起。新华社《学习进行时》原创品牌栏目“讲习所”为您梳理总书记划出的重点。");
        newsInfo.setState(1);
        newsInfo.setReadCount(1086L);

        NewsInfo newsInfo1 = new NewsInfo();
        newsInfo1.setId(UUID.randomUUID().toString());
        newsInfo1.setTitle("中国商务部:中美就经贸问题举行副部级磋商");
        newsInfo1.setSubTitle("");
        newsInfo1.setPublicTime("2018-08-24 08:56:37");
        newsInfo1.setAuthor("商务部");
        newsInfo1.setContent("应美方邀请，中国商务部副部长兼国际贸易谈判副代表王受文率中方代表团于8月22日至23日在华盛顿与美国财政部副部长马尔帕斯率领的美方代表团就双方关注的经贸问题进行了建设性、坦诚的交流。双方将就下一步安排保持接触。");
        newsInfo1.setState(1);
        newsInfo1.setReadCount(513L);

        List<NewsInfo> newsInfos = new ArrayList<>();
        newsInfos.add(newsInfo);
        newsInfos.add(newsInfo1);
        boolean b = newsService.bulkSaveNews(newsInfos);
        Assert.assertTrue("save failed.", b);
    }

    @Test
    public void findTitleByFieldTest() {
        Map<String, Object> fieldContentMap = new HashMap<>();
        fieldContentMap.put("title", "习近平");
        fieldContentMap.put("content", "习近平");
        List<String> highlightField = new ArrayList<>();
        highlightField.add("title");
        highlightField.add("content");
        Map<String, Object> titleByField = newsService.findTitleByField(fieldContentMap, highlightField);
        System.out.println(titleByField.size());
    }
}
