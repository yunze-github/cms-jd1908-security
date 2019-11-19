package com.briup.apps.cms;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.dao.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmsApplicationTests {

    @Autowired
    private ArticleMapper mapper;

    @Test
    void contextLoads() {
        List<Article> examples = mapper.selectByExample(new ArticleExample());
        examples.forEach(System.out::println);
    }

}
