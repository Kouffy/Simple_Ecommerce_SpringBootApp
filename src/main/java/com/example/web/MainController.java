package com.example.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Article;
import com.example.model.Panier;
import com.example.service.ArticleService;
import com.example.service.EmailService;
import com.example.service.PanierService;
import com.example.service.UserService;


@Controller
public class MainController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private PanierService panierService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	
	@GetMapping("/login")
	public String login()
	{
		return "login";}

	
	   @GetMapping("/")
	    public String homeUser(Model model) {
	    	model.addAttribute("articles" ,articleService.getAllArticles());
	        return "index";
	    }
	   
	   @GetMapping("/{id}")
	   public String Onearticle(Model model,@PathVariable Long id)
	   {
		  model.addAttribute("article", articleService.getArticle(id)) ;
		   return "description";
	   }
	   
	
	   @PostMapping("/addtocart")
	    public String ajouterAuPanier(Model model,@RequestParam(name="id") Long id,@RequestParam(name="qte") Long qte) {
	    	 Article art;String username="";
	    	
	    
	    	art=articleService.getArticle(id);
	    	Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if(principal instanceof UserDetails)
	    	{
	    		username=((UserDetails)principal).getUsername();
	    	
	    	}
	    	else
	    	{
	    		 username=principal.toString();
	    	}
	    	
	    	
	    	if(art.getQte_stock()>=qte)
	    	{
	    		panierService.savePanier(new Panier(art,userService.findbymail(username),qte));
	    	}
	    	model.addAttribute("articles" ,articleService.getAllArticles());
	        return "index";
	    }
	   
	   @GetMapping("/monPanier")
	   public String monPanier(Model model)
	   {
		   String username="";
			Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if(principal instanceof UserDetails)
	    	{
	    		username=((UserDetails)principal).getUsername();
	    	
	    	}
	    	else
	    	{
	    		 username=principal.toString();
	    	}
		   model.addAttribute("paniers",panierService.getPanierByUser(userService.findbymail(username)));
		   return "monPanier";
	   }
	   @GetMapping("/validation")
	   public String validation(Model model) throws Exception
	   {
		   String username="";
			Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if(principal instanceof UserDetails)
	    	{
	    		username=((UserDetails)principal).getUsername();
	    	
	    	}
	    	else
	    	{
	    		 username=principal.toString();
	    	}
		List<Panier> paniers=panierService.getPanierByUser(userService.findbymail(username));
		String[] columns = {"ID", "Libelle", "Prix", "Qte commande"};
		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Panier");
		 Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        Row headerRow = sheet.createRow(0);
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        
	        int rowNum = 1;
	        for(Panier panier: paniers) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0)
	                    .setCellValue(panier.getArticle().getId());

	            row.createCell(1)
	                    .setCellValue(panier.getArticle().getLibelle());
	         
	            row.createCell(2)
	                    .setCellValue(panier.getArticle().getPrix());
	            row.createCell(3)
                .setCellValue(panier.getQte_cmd());
	        }
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        
	        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
	        File file =new File("poi-generated-file.xlsx");
	        emailService.sendMail("soufyan.mamado@gmail.com", "subject", "message", file);
	        List<Article> art=articleService.getAllArticles();
	        for (Article article : art) {
				for (Panier panier : paniers) {
					if(panier.getArticle().getId()==article.getId())
					{
						article.setQte_stock(article.getQte_stock()-panier.getQte_cmd());
						articleService.saveArticle(article);
					}
				}
			}
	        panierService.deletePanierByUser(userService.findbymail(username));
			model.addAttribute("articles" ,articleService.getAllArticles());
		   return "index";
	   }
}
