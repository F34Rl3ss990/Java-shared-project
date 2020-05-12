package com.BD.MZS.Article.controller;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.BD.MZS.Article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Date;

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

    @GetMapping(value = "/AddArticle")
    public ModelAndView showForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("AddArticle");
        mav.addObject("ArticleDTO", new ArticleDTO());
        ArticleDTO.counter();
        ArticleDTO.setDateofCreate(new Date());
        return mav;
    }
    @PostMapping(value ="/AddArticle")
    public ModelAndView AddArticle(@Valid ArticleDTO articleDTO,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("AddArticle");
        }
        System.out.println(articleDTO);
        articleService.add(articleDTO);
    return new ModelAndView("redirect:/GetArticle");
    }
    @GetMapping(value="/GetArticle")
    public ModelAndView getArticles(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("GetArticle");
        mav.addObject("Articles", articleService.listAll());
        return mav;
    }
    @PostMapping(value="/GetArticle")
    public ModelAndView postArticles(@RequestParam(value="ISBN") int ISBN){
            return new ModelAndView("redirect:/ModifyArticle?ISBN=" + ISBN);
    }

    @GetMapping(value="/GetArticle/Delete{ISBN}")
    public ModelAndView postArticlez(@RequestParam(value="ISBN") int ISBN){
        articleService.deleteByID(ISBN);
        System.out.println("asdasdas");
        return new ModelAndView("redirect:/");

    }
    @GetMapping(value="/ModifyArticle{ISBN}")
    public ModelAndView modifyArticles(@RequestParam(value="ISBN") @PathVariable int ISBN){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ModifyArticle");
        mav.addObject("article", articleService.getByID(ISBN));
        return mav;
    }

    @PostMapping(value="/ModifyArticle{ISBN}")
    public ModelAndView modifyPostArticles(@RequestParam(value="ISBN") @PathVariable int ISBN,
                                           @Valid ArticleDTO article, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("ModifyArticle{ISBN}");
        }
        articleService.modByID(article);
        return new ModelAndView("redirect:/GetArticle");
    }


}
