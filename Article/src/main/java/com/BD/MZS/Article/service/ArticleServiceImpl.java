package com.BD.MZS.Article.service;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.BD.MZS.Article.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public void setEntityRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @Override
    public void add(ArticleDTO articleDTO) {
        articleRepository.save(articleDTO);
    }
    @Override
    public List<ArticleDTO> listAll() {
        return articleRepository.getAll();
    }

    @Override
    public void deleteByID(int isbn) {
        articleRepository.deleteByID(isbn);
    }

    @Override
    public ArticleDTO getByID(int isbn) {
       return articleRepository.getByID(isbn);
    }

    @Override
    public void modByID(ArticleDTO articleDTO) {
            articleRepository.modByID(articleDTO);
    }
}
