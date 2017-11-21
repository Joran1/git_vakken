package controller;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import domain.*;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopService shop;
	private HttpSession session;
	private Cookie cookie = new Cookie("color", "yellow");;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();

		Properties properties = new Properties();
		Enumeration<String> paramNames = context.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String propertyName = paramNames.nextElement();
			properties.setProperty(propertyName, context.getInitParameter(propertyName));
		}

		shop = new ShopService(properties);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = "index.jsp";
		String action = request.getParameter("action");

		switch (action) {
		case "userOverview":
			destination = "personoverview.jsp";
			request.setAttribute("users", shop.getPersons());
			break;
		case "productOverview":
			destination = "productoverview.jsp";
			request.setAttribute("products", shop.getProducts());
			break;
		case "productForm":
			destination = "productForm.jsp";
			break;
		case "signUp":
			destination = "signUp.jsp";
			break;
		case "addUser":
			destination = this.addUser(request, response);
			break;
		case "addProduct":
			destination = this.addProduct(request, response);
			break;
		case "updateProduct":
			destination = this.updateProduct(request, response);
			break;
		case "productUpdateConfirmed":
			destination = this.updateProductConfirmed(request, response);
			break;
		case "deleteProduct":
			request.setAttribute("id", request.getParameter("id"));
			destination = "deleteProduct.jsp";
			break;
		case "deleteUser":
			request.setAttribute("id", request.getParameter("id"));
			destination = "deleteUser.jsp";
			break;
		case "productdeleteConfirmed":
			destination = this.deleteProduct(request, response);
			break;
		case "userdeleteConfirmed":
			destination = this.deletePerson(request, response);
			break;
		case "checkPassword":
			request.setAttribute("id", request.getParameter("id"));
			destination = "checkPassword.jsp";
			break;
		case "checkPasswordConfirmed":
			destination = this.checkPassword(request, response);
			break;
		case "changeColor":
			destination = this.changeColor(request, response);
			break;
		case "login":
			destination = this.login(request, response);
			break;
		case "logout":
			destination = this.logout(request, response);
			break;
		default:
			destination = "index.jsp";
		}

		request.setAttribute("color", cookie.getValue());
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		List<Person> persons = shop.getPersons();

		for (Person p : persons) {
			if (p.getEmail().equals(email)) {
				if(p.isCorrectPassword(password)){
					session.setAttribute("user", p);
				}
			}
		}

		return "index.jsp";
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		if (this.session != null) {
			session.invalidate();
		}

		return "index.jsp";

	}

	private String checkPassword(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Person p = shop.getPerson(id);

		if (p.isCorrectPassword(password)) {
			request.setAttribute("check", "juist");
		} else {
			request.setAttribute("check", "fout");
		}

		return "checkPassword.jsp";
	}

	private String changeColor(HttpServletRequest request, HttpServletResponse response) {
		cookie.setMaxAge(-1);
		response.addCookie(cookie);

		Cookie[] cookies = null;
		cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getValue().equals(("yellow"))) {
					cookie.setValue("red");
				} else if (cookies[i].getValue().equals(("red"))) {
					cookie.setValue("yellow");
				}
			}
		}

		request.setAttribute("color", cookie.getValue());
		response.addCookie(cookie);
		return "Controller?action=" + request.getParameter("page");
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String keuze = request.getParameter("submit");

		if (keuze.equals("ja")) {
			shop.deleteProduct(Integer.parseInt(request.getParameter("id")));
		}

		request.setAttribute("products", shop.getProducts());
		return "productoverview.jsp";
	}

	private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
		String keuze = request.getParameter("submit");

		if (keuze.equals("ja")) {
			shop.deletePerson(request.getParameter("id"));
		}

		request.setAttribute("users", shop.getPersons());
		return "personoverview.jsp";
	}

	private String addUser(HttpServletRequest request, HttpServletResponse response) {
		String destination = "signUp.jsp";
		ArrayList<String> errors = new ArrayList<>();
		Person p = new Person();

		try {
			p.setFirstName(request.getParameter("firstName"));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setLastName(request.getParameter("lastName"));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setEmail(request.getParameter("email"));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
		
		try {
			p.setRole(Role.CUSTOMER);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
		
		try {
			p.setPasswordHashed(request.getParameter("password"));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		if (errors.isEmpty()) {
			shop.addPerson(p);
			request.setAttribute("products", shop.getProducts());
			destination = "personoverview.jsp";
		} else {
			request.setAttribute("errors", errors);
		}

		return destination;
	}

	private String addProduct(HttpServletRequest request, HttpServletResponse response) {
		String destination = "productForm.jsp";
		ArrayList<String> errors = new ArrayList<>();
		Product p = new Product();

		try {
			p.setName(request.getParameter("name"));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setDescription(request.getParameter("description"));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setPrice(request.getParameter("price"));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}

		if (errors.isEmpty()) {
			shop.addProduct(p);
			request.setAttribute("products", shop.getProducts());
			destination = "productoverview.jsp";
		} else {
			request.setAttribute("errors", errors);
		}

		return destination;

	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		Product pr = shop.getProduct(Integer.parseInt(id));
		request.setAttribute("id", id);
		request.setAttribute("name", pr.getName());
		request.setAttribute("description", pr.getDescription());
		request.setAttribute("price", pr.getPrice());

		return "updateProduct.jsp";
	}

	private String updateProductConfirmed(HttpServletRequest request, HttpServletResponse response) {
		String destination = "updateProduct.jsp";
		ArrayList<String> errors = new ArrayList<>();
		Product p = shop.getProduct(Integer.parseInt(request.getParameter("id")));

		try {
			p.setName(request.getParameter("name"));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setDescription(request.getParameter("description"));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
		try {
			p.setPrice(Double.parseDouble(request.getParameter("price")));
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}

		if (errors.isEmpty()) {
			shop.updateProduct(p);
			destination = "productoverview.jsp";
			request.setAttribute("products", shop.getProducts());
		} else {
			request.setAttribute("errors", errors);
		}

		return destination;
	}
}