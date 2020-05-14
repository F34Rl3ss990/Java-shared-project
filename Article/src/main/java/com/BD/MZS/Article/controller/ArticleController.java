package com.BD.MZS.Article.controller;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.BD.MZS.Article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setEntityService(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping(value = "/")
    public ModelAndView getRoot() {
        return new ModelAndView("MainArticle");
    }

    @GetMapping(value = "/AddArticle")
    public ModelAndView addArticleForm() {
        ArticleDTO.setDateCreate(new Date());
        return new ModelAndView("AddArticle").addObject("articleDTO", new ArticleDTO());
    }

    @PostMapping(value = "/AddArticle")
    public ModelAndView AddArticle(@Valid ArticleDTO articleDTO,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("AddArticle").addObject("articleDTO", articleDTO);
        }
        articleService.add(articleDTO);
        ArticleDTO.counter();
        return new ModelAndView("redirect:/GetArticle");
    }

    @GetMapping(value = "/GetArticle")
    public ModelAndView getArticles(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam(required = false, value="article")String article,
                                    @RequestParam(required = false, value="filter")String filter,
                                    @RequestParam(required = false, value="ascOrDesc")String ascOrDesc) {
        ModelAndView mav = new ModelAndView();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<ArticleDTO> articlePage = articleService.findPaginated(PageRequest.of(currentPage - 1, pageSize), article, filter, ascOrDesc);
        int totalPages = articlePage.getTotalPages();
        mav.addObject("articlePage", articlePage);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }
        mav.setViewName("GetArticle");
        return mav;
    }

    @PostMapping(value = "/GetArticle")
    public ModelAndView modifyArticlePost(@RequestParam(required = false,value = "ISBN") int ISBN) {
        return new ModelAndView("redirect:/ModifyArticle?ISBN=" + ISBN);
    }

    @GetMapping(value = "/GetArticle/Delete{ISBN}")
    public ModelAndView deleteArticle(@RequestParam(value = "ISBN") int ISBN) {
        articleService.deleteByID(ISBN);
        return new ModelAndView("MainArticle");
    }

    @GetMapping(value = "/GetArticle/Open{ISBN}")
    public ModelAndView openArticle(@RequestParam(value = "ISBN") int ISBN) {
        return new ModelAndView("OpenArticle").addObject("article", articleService.getByID(ISBN));
    }

    @GetMapping(value = "/ModifyArticle{ISBN}")
    public ModelAndView getModifyArticleForm(@RequestParam(value = "ISBN") @PathVariable int ISBN) {
        return new ModelAndView("ModifyArticle").addObject("article", articleService.getByID(ISBN));
    }

    @PostMapping(value = "/ModifyArticle{ISBN}")
    public ModelAndView modifyPostArticles(@RequestParam(value = "ISBN") @PathVariable int ISBN,
                                           @Valid ArticleDTO article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("ModifyArticle").addObject("article", article);
        }
        articleService.modByID(article);
        return new ModelAndView("redirect:/GetArticle");
    }

}
