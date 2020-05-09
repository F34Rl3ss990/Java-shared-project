package com.BD.MZS.Article.service;

import com.BD.MZS.Article.controller.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    void add(ArticleDTO articleDTO);
    List<ArticleDTO> listAll();
    void deleteByID(int isbn);
    ArticleDTO getByID(int isbn);
    void modByID(ArticleDTO articleDTO);
}
