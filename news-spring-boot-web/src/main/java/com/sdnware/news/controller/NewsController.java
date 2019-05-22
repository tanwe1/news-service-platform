package com.sdnware.news.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdnware.news.api.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sdnware on 18-8-23.
 */
@RestController
@RequestMapping(value = "news")
public class NewsController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.reference.url}")
    private NewsService newsService;

    @PutMapping(value = "add")
    public void addNews() {
        /**NewsInfo newsInfo = new NewsInfo();
        newsInfo.setId(UUID.randomUUID().toString());
        newsInfo.setTitle("习近平：举旗帜聚民心育新人兴文化展形象 更好完成新形势下宣传思想工作使命任务");
        newsInfo.setSubTitle("");
        newsInfo.setPublicTime("2018-08-22 23:26:34");
        newsInfo.setAuthor("新华网");
        newsInfo.setContent("新华社北京8月22日电 全国宣传思想工作会议21日至22日在北京召开。中共中央总书记、国家主席、中央军委主席习近平出席会议并发表重要讲话。他强调，完成新形势下宣传思想工作的使命任务，必须以新时代中国特色社会主义思想和党的十九大精神为指导，增强“四个意识”、坚定“四个自信”，自觉承担起举旗帜、聚民心、育新人、兴文化、展形象的使命任务，坚持正确政治方向，在基础性、战略性工作上下功夫，在关键处、要害处下功夫，在工作质量和水平上下功夫，推动宣传思想工作不断强起来，促进全体人民在理想信念、价值理念、道德观念上紧紧团结在一起，为服务党和国家事业全局作出更大贡献。");
        newsInfo.setState(1);
        newsInfo.setReadCount(1813L);
        newsService.saveNews(newsInfo);
        return newsInfo;**/
        Map<String, String> newsInfos = new HashMap<>();
        newsInfos.put("id", UUID.randomUUID().toString());
        newsInfos.put("title", "");
        newsInfos.put("subTitle", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");
        newsInfos.put("", "");

    }

    @DeleteMapping(value = "delete")
    public void delNews(@RequestParam String id) {
        newsService.delNewsById(id);
    }

    @DeleteMapping(value = "delAll")
    public void delAllNews() {
        newsService.delAll();
    }
}
