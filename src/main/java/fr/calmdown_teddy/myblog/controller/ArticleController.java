package fr.calmdown_teddy.myblog.controller;

import fr.calmdown_teddy.myblog.model.Article;
import fr.calmdown_teddy.myblog.repository.ArticleRepository;
import fr.calmdown_teddy.myblog.service.ArticleService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {

        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();

        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

        Article updatedArticle = articleService.updateArticle(id, articleDetails);
        if (updatedArticle == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedArticle);
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<Article>> getArticlesByTitle(@RequestParam String searchTerms) {
        List<Article> articles = articleRepository.findByTitle(searchTerms);

        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search-content")
    public ResponseEntity<List<Article>> getArticlesByContent(@RequestParam String searchTerms) {
        List<Article> articles = articleRepository.findByContentContaining(searchTerms);

        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(articles);
    }

    @GetMapping("/created-after")
    public ResponseEntity<List<Article>> getArticlesCreatedAfter(@RequestParam LocalDateTime date) {

        List<Article> articles = articleRepository.findByCreatedAtAfter(date);

        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(articles);
    }

    @GetMapping("/last-five")
    ResponseEntity<List<Article>> getLastFiveArticles(@RequestParam LocalDateTime date) {

        List<Article> articles = articleRepository.findTopFiveByOrderByCreatedAt(date);

        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(articles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
