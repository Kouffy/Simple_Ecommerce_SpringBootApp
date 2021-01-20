package com.example.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadUtil;
import com.example.model.Article;
import com.example.model.Panier;
import com.example.service.ArticleService;
import com.example.service.PanierService;


@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private PanierService panierService;
	
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
    public String saveArticle(Article article, @RequestParam("file") MultipartFile multipartFile)throws IOException {
       
         
        String fileName = multipartFile.getOriginalFilename();
        System.out.println(fileName);
        article.setImage(fileName);
        
articleService.saveArticle(article);
    
        String uploadDir = "resources/static/article-photos/";
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    
        return "redirect:/article/getall";
    }
    

    
    @GetMapping("/getall")
    public String getAllArticles(Model model) {
    	int checker=0;
    	model.addAttribute("articles" ,articleService.getAllArticles());
    	model.addAttribute("checker",checker);
    	System.out.print("get all"+ checker);
        return "ListeArticles";
    }
    @GetMapping("/getall/{checker}")
    public String getAllArticles(Model model,@PathVariable(name = "checker") Long checker) {
    	
    	model.addAttribute("articles" ,articleService.getAllArticles());
        model.addAttribute("checker",checker);
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
    public String deleteEmployee(Model model,@PathVariable(name = "articleId") Long articleId) {
        int checke=0;
    	if(panierService.getAllPanier()!=null)
    	{
    	List<Panier> paniers =panierService.getAllPanier();
    	for (Panier panier : paniers) {
			if(panier != null)
			{
				if(panier.getArticle()!=null)
				{
					if(panier.getArticle().getId()==articleId)
						checke++;
				}
				
			}
		}  
    	if(checke==0)
    	{
        articleService.deleteArticle(articleId);
    	 model.addAttribute("checker",checke);
    	}
        
    	}
    	return "redirect:/article/getall/"+checke;
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
        article.setImage("kjg");
        articleService.updateArticle(article);
        return "redirect:/article/getall";
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
