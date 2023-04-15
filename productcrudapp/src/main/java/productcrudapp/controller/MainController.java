package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model model) {
//		List<Product> products = productDao.getProducts();
//		model.addAttribute("products", products);
		return "home";
	}
	
//	@RequestMapping("/")
//	public String home(Model model) {
//		List<Product> products = productDao.getProducts();
//		model.addAttribute("products", products);
//		return "index";
//	}
	
	
	@RequestMapping("/home")
	public String home1(Model model) {
		List<Product> products = productDao.getProducts();
		model.addAttribute("products", products);
		return "index";
	}

	// show add product from
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add Product");
		return "add_product_form";
	}

	// handle add product from
	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request) {
		System.out.println(product);

		productDao.createProduct(product);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}

	// delete handler
	@RequestMapping("/delete-product/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId ,HttpServletRequest request) {
		
		this.productDao.deleteProduct(productId);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	
	// update handler
	@RequestMapping("/update-product/{productId}")
	public String updateProduct(@PathVariable("productId") int pid, Model model)
	{
		Product product = this.productDao.getProduct(pid);
		model.addAttribute("product",product);
		return "update_from";
	}
	
	
	
	
	

}
