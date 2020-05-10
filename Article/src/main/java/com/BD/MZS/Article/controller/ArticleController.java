package com.BD.MZS.Article.controller;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.BD.MZS.Article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

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

    @GetMapping(value = "/GetArticle")
    public ModelAndView getListed(@RequestParam(value="age", required=false, defaultValue="5") String age, Model model){
        ModelAndView mav = new ModelAndView();
        model.addAttribute("age", age);
        mav.setViewName("GetArticle");
        return mav;
    }
/*
    @PostMapping(value ="/")
    public ModelAndView AddArticle(@Valid ArticleDTO articleDTO,
                                   BindingResult bindingResult){

    }*/
}
