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
        System.out.println("rák");
        return new ModelAndView("redirect:/ModifyArticle");
    }


    /*@GetMapping(value = "/GetArticle")
    public ModelAndView getListed(@RequestParam(value="title", required=false, defaultValue="anyád") String title, Model model,
                                  @RequestParam(value = "article", required = false, defaultValue = "LOREMFASZ") String article,
                                  @RequestParam(value = "author", required = false, defaultValue = "anyám") String author,
                                  @RequestParam(value = "isbn", required = false, defaultValue = "4") String isbn,
                                  @RequestParam(value = "created", required = false, defaultValue = "1000.01.01") String created,
                                  @RequestParam(value = "lmodif", required = false, defaultValue = "2000.02.02") String lmodif) {

        ModelAndView mav = new ModelAndView();
        model.addAttribute("title", title);
        model.addAttribute("article", article);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("created", created);
        model.addAttribute("lmodif", lmodif);
        mav.setViewName("GetArticle");
        return mav;
    }*/


}
