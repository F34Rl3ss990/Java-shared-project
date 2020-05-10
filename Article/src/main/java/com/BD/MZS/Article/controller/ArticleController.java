package com.BD.MZS.Article.controller;

import com.BD.MZS.Article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setEntityService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/")
    public ModelAndView getRoot() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("MainArticle");
        return mav;
    }
}
