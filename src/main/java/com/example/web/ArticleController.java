package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.Article;
import com.example.service.ArticleService;
import com.example.web.dto.UserRegistrationDto;


@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@GetMapping(path = "/hello")
    public String getMessage() {
        return "Hello boot";
    }

	@GetMapping("save")
	public String showSaveForm(Article article) {
		return "ajouterArticle";
	}
    @PostMapping("/saveArt")
    public String saveArticle(@Validated Article article,BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "save";
        }
        articleService.saveArticle(article);
        return "redirect:/article/getall";
        
    }
    
    @GetMapping("/edit/{articleId}/")
    public String showEditForm(@PathVariable(name = "articleId") Long articleId,Model model) {
        Article article = null;
        try {
            article = articleService.getArticle(articleId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("article", article);
    	return "ModifierArticle";
    }
    @PutMapping("/update")
    public Article updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @GetMapping("/getall")
    public String getAllArticles(Model model) {
    	model.addAttribute("articles" ,articleService.getAllArticles());
        return "ListeArticles";
    }

    @GetMapping("/getone/{articleId}")
    public Article getEmployee(@PathVariable(name = "articleId") Long articleId) {
        return articleService.getArticle(articleId);
    }

    @GetMapping("/delete/{articleId}")
    public String deleteEmployee(@PathVariable(name = "articleId") Long articleId) {
    	articleService.deleteArticle(articleId);
    	return "ListeArticles";
    }

}
