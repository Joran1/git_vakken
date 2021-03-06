package ui.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbException;
import domain.DomainException;
import domain.Person;
import domain.Product;
import domain.ShopService;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShopService service;
	private Cookie cookie = new Cookie("color", "yellow");;
	
	public void init() throws ServletException{
		super.init();
		
		ServletContext context = getServletContext();
		
		Properties properties = new Properties();
		Enumeration<String> parameterNames = context.getInitParameterNames();
		while(parameterNames.hasMoreElements()) {
			String propertyName = parameterNames.nextElement();
			properties.setProperty(propertyName, context.getInitParameter(propertyName));
		}		
		service = new ShopService(properties);
	}
	
    public servlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verwerkRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verwerkRequest(request, response);
	}
	
	protected void verwerkRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String doel;
		String actie = request.getParameter("action");
		if(actie == null) actie = "";
		
		Cookie q = null;
		if(request.getCookies() != null){
			for (Cookie c : request.getCookies()){
				if (c.getName().equals("color")){
					q = c;
					break;
				}
			}
		}
		
		if(q == null){
			q = new Cookie("color","yellow");
		}
		String color = q.getValue();
		if(actie.equals("changeColor")){
			actie = changeColor(request,response, q);
			color = q.getValue();
		}
		if(color.equals("yellow") ||color.equals("red")){
			request.setAttribute("color", color);
		}
		
		switch(actie) {
		
		//NAVIGATION
		case "naarPersonOverview":
			doel = toonPersonen(request, response);
			break;		
		case "naarMaakPersoon":
			doel = "signUp.jsp";
			break;
			
		case "naarProductOverview":
			doel = toonProducten(request, response);
			break;
		case "naarMaakProduct":
			doel = "maakProduct.jsp";
			break;
			
		//AANMAAK
		case "maakPersoon":
			doel = maakPersoon(request, response);
			break;
		case "maakProduct":
			doel = maakProduct(request, response);
			break;
			
		//VERWIJDER
		case "naarDeletePersoon":
			doel = "ConfirmDeletePerson.jsp";
			break;
		case "naarDeleteProduct":
			doel = "ConfirmDeleteProduct.jsp";
			break;
		
		case "DeletePerson":
			doel = verwijderPersoon(request, response);
			break;
		case "DeleteProduct":
			doel = verwijderProduct(request, response);
			break;
		
		//UPDATE
		case "naarUpdate":
			doel = naarUpdate(request,response);
			break;
		case "Update":
			doel = update(request,response);
			break;
			
		//CHECK PASSWORD
		case "naarCheckPassword":
			request.setAttribute("id", request.getParameter("id"));
			doel = "CheckPassword.jsp";
			break;			
		case "checkPassword":
			doel = checkPassword(request, response);
			break;
			
		//CHANGE COLOR
		case "changeColor":
			doel = changeColor(request, response, q);
			break;
			
		default:
			doel = "index.jsp";
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(doel);
		rd.forward(request, response);
	}

	//MAAK PERSOON
	private String maakPersoon(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Person person = new Person();
		List<String> foutenPersoon = new ArrayList<String>();
		
		verwerkId(person, request, foutenPersoon);
		verwerkEmail(person, request, foutenPersoon);
		verwerkPassword(person, request, foutenPersoon);
		verwerkFirstName(person, request, foutenPersoon);
		verwerkLastName(person, request, foutenPersoon);
		
		if(foutenPersoon.isEmpty()) { try { service.addPerson(person); } catch (DbException db) { foutenPersoon.add("User already exists");} }
		if(foutenPersoon.isEmpty()) {
			return "index.jsp"; 
		} else {
			request.setAttribute("foutenPersoon", foutenPersoon);
			return "signUp.jsp";
		}
	}
	//MAAK PRODUCT
	private String maakProduct(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Product product = new Product();
		List<String> foutenProduct = new ArrayList<String>();
		
		verwerkName(product, request, foutenProduct);
		verwerkDescription(product, request, foutenProduct);
		verwerkPrijs(product, request, foutenProduct);
		
		try { if(foutenProduct.isEmpty()) { service.addProduct(product); }} catch (DbException db) { foutenProduct.add("Product bestaat al");}
		if(foutenProduct.isEmpty()) {
			return toonProducten(request,response);
		} else {
			request.setAttribute("foutenProduct", foutenProduct);
			return "maakProduct.jsp";
		}	
	}
	
	//VERWERKEN PARAMETERS PERSOON
	private void verwerkId(Person person, HttpServletRequest request, List<String> foutenPersoon) {
		String id = request.getParameter("userid");
		request.setAttribute("userid", id);
		try { person.setUserid(id); } catch (IllegalArgumentException e){ foutenPersoon.add(e.getMessage()); }
	}
	private void verwerkEmail(Person person, HttpServletRequest request, List<String> foutenPersoon) {
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		try { person.setEmail(email); } catch (IllegalArgumentException e){ foutenPersoon.add(e.getMessage()); }
	}
	private void verwerkPassword(Person person, HttpServletRequest request, List<String> foutenPersoon) {
		String password = request.getParameter("password");
		request.setAttribute("password", password);
		try { 
			person.setPasswordHashed(password); 
		} catch (IllegalArgumentException a){ foutenPersoon.add(a.getMessage());
		} catch (NoSuchAlgorithmException b){ foutenPersoon.add(b.getMessage());
		} catch (UnsupportedEncodingException c){ foutenPersoon.add(c.getMessage());
		}
	}
	private void verwerkFirstName(Person person, HttpServletRequest request, List<String> foutenPersoon) {
		String firstName = request.getParameter("firstName");
		request.setAttribute("firstName", firstName);
		try { person.setFirstName(firstName); } catch (IllegalArgumentException e){ foutenPersoon.add(e.getMessage()); }
	}
	private void verwerkLastName(Person person, HttpServletRequest request, List<String> foutenPersoon) {
		String lastName = request.getParameter("lastName");
		request.setAttribute("lastName", lastName);
		try { person.setLastName(lastName); } catch (IllegalArgumentException e){ foutenPersoon.add(e.getMessage()); }
	}
	//VERWERKEN PARAMETERS PRODUCT
	private void verwerkName(Product product, HttpServletRequest request, List<String> foutenProduct) {
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		try { product.setName(name); } catch (DomainException e){ foutenProduct.add(e.getMessage()); }
	}
	private void verwerkDescription(Product product, HttpServletRequest request, List<String> foutenProduct) {
		String description = request.getParameter("description");
		request.setAttribute("description", description);
		try { product.setDescription(description); } catch (DomainException e){ foutenProduct.add(e.getMessage()); }
	}
	private void verwerkPrijs(Product product, HttpServletRequest request, List<String> foutenProduct) {	
		try { 
			double price = Double.parseDouble(request.getParameter("price"));
			request.setAttribute("price", price);
			product.setPrice(price); 
		} catch (NumberFormatException f){ 
			foutenProduct.add("prijs is geen cijfer"); 
		} catch (DomainException e){ 
			foutenProduct.add(e.getMessage()); 
		}
	}
	
	//TONEN OVERVIEWS
	private String toonPersonen(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("personen", service.getPersons());
		return "personoverview.jsp";
	}	
	private String toonProducten(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("producten", service.getProducts());
		return "productoverview.jsp";
	}
	
	//VERWIJDER
	private String verwijderProduct(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		service.deleteProduct(id);
		return toonProducten(request, response);
	}
	private String verwijderPersoon(HttpServletRequest request, HttpServletResponse response) {
		String id = (request.getParameter("id"));
		service.deletePerson(id);
		return toonPersonen(request, response);
	}
	
	//UPDATE
	private String naarUpdate(HttpServletRequest request, HttpServletResponse response) {
			String id = request.getParameter("idUpdate");	
			Product product = service.getProduct(Integer.parseInt(id));
			request.setAttribute("p", product);
		return "Update.jsp";
	}	
	private String update(HttpServletRequest request, HttpServletResponse response) {
		String productId = (request.getParameter("idUpdate"));
		Product product = service.getProduct(Integer.parseInt(productId));
		List<String> foutenProduct = new ArrayList<String>();
		
		verwerkName(product, request, foutenProduct);
		verwerkDescription(product, request, foutenProduct);
		verwerkPrijs(product, request, foutenProduct);
		
		if(foutenProduct.isEmpty()) { 
			try { 
				service.updateProducts(product); 
			} catch (DbException db) {
				foutenProduct.add(db.getMessage());
			}
		}
		if(foutenProduct.isEmpty()) {
			return toonProducten(request, response);
		} else {
			request.setAttribute("foutenProduct", foutenProduct);
			return "Update.jsp";
		}	
	}
	
	//CHECK PASSWORD
	private String checkPassword(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Person p = service.getPerson(id);
		try {
		if(p.isCorrectPassword(password)){ request.setAttribute("check", "juist"); } else { request.setAttribute("check", "fout"); }
		}catch(UnsupportedEncodingException e){
		}catch(NoSuchAlgorithmException d){ 
		}
		
		return "CheckPassword.jsp";		
	}

	//CHANGE COLOR
	private String changeColor(HttpServletRequest request, HttpServletResponse response, Cookie c) throws ServletException, IOException {
		String origin = request.getParameter("page");
		if(c.getValue().equals("yellow")){
			c.setValue("red");
		}else{
			c.setValue("yellow");
		}
		response.addCookie(c);
		return origin;
	}

}
