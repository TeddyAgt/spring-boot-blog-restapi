package fr.calmdown_teddy.myblog.service;

import fr.calmdown_teddy.myblog.model.Article;
import fr.calmdown_teddy.myblog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        return article;
    }

    public Article createArticle(Article article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        Article savedArticle = articleRepository.save(article);

        return savedArticle;
    }

    public updateArticle(Long id, Article articleDetails) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        Article updatedArticle = articleRepository.save(article);
        return updatedArticle;
    }

    public deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(Null);
        if (article == null) {
            return false;
        }

        articleRepository.delete(id);
        return true;
    }
}
