package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.dao.extend.CommentExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    //博客映射接口
    @Autowired
    private ArticleExtendMapper articleExtendMapper;



    @Override
    public List<ArticleExtend> findAllArticleInCategoryByCategoryId(long categoryId) {
        return articleExtendMapper.selectAllArticleWithCategoryByCategoryId(categoryId);
    }

    @Override
    public List<ArticleExtend> findAllArticle() {
        return articleExtendMapper.selectAllArticle();
    }

    @Override
    public ArticleExtend findArtiacleInCateGoryWithCommentByID(long articleId) {
        return articleExtendMapper.selectArtiacleWithCateGoryAndCommentByID(articleId);
    }


    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void saveOrUpdate(Article article) throws CustomerException {
        //先判断articleId是否存在
        if (article.getId()!=null) {
            articleMapper.updateByPrimaryKey(article);
        }else {
            ArticleExample example = new ArticleExample();
            example.createCriteria().andTitleEqualTo(article.getTitle());
            List<Article> list = articleMapper.selectByExample(example);
            if (list.size()>0) {
                throw new CustomerException("文章标题不能重复！");
            }
            //存入article
            articleMapper.insert(article);
        }
    }

    @Override
    public void dropOne(Long articleId) {
        articleMapper.deleteByPrimaryKey(articleId);
    }
}
