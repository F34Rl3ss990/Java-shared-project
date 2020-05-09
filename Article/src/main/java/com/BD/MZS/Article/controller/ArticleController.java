package com.BD.MZS.Article.controller;

import com.BD.MZS.Article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setEntityService(ArticleService articleService) {
        this.articleService = articleService;
    }

}
