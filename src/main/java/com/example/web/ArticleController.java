package com.example.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Article;
import com.example.service.ArticleService;


@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	Map<Long, Article> artStores = new HashMap<Long, Article>();
	
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
    
  /*  @GetMapping("/edit/{articleId}/")
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
    }*/

    
    @GetMapping("/getall")
    public String getAllArticles(Model model) {
    	model.addAttribute("articles" ,articleService.getAllArticles());
        return "ListeArticles";
    }
    

    @RequestMapping(value = "/apiall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
	public List <Article> getResource() {
    	List <Article> a = articleService.getAllArticles();
	return a;
    }
    
    @GetMapping("/getone/{articleId}")
    public Article getEmployee(@PathVariable(name = "articleId") Long articleId) {
        return articleService.getArticle(articleId);
    }

    @GetMapping("/delete/{articleId}")
    public String deleteEmployee(@PathVariable(name = "articleId") Long articleId) {
    	articleService.deleteArticle(articleId);
    	return "redirect:/article/getall";
    }
    
    
    
    
    
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "ModifierArticle";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,  Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	article.setId(id);
            return "ModifierArticle";
        }
        articleService.updateArticle(article);
        return "redirect:/article/getall";
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
