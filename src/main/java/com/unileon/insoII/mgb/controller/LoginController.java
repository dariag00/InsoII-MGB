package com.unileon.insoII.mgb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.UserService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value={"/login", "/"}, method = RequestMethod.POST)
	public String showMainPage(ModelMap model, @RequestParam String email, @RequestParam String password, HttpSession session) {
		boolean isValidUser = loginService.logIn(email, password, session);
		if(isValidUser) {
			User user = (User) session.getAttribute("user");
			if(user.isFirstLogin()) {
				model.addAttribute("user", user);
				Account newAccount = user.getListOfAccounts().get(0);
				model.addAttribute("account", newAccount);
				for(Card card: user.getCards()) {
					model.addAttribute("card", card);
				}
				
				return "importantInformation";
			}
			return "redirect:dashboard";
		}else {
			model.addAttribute("errorMessage", "EL usuario no existe o las contraseñas no coinciden");
			return "login";
		}
	}
	
	@RequestMapping(value={"/create_account"}, method = RequestMethod.GET)
	public String showCreateAccountPage(ModelMap model) {
		model.addAttribute("user", new UserForm());
		//model.addAttribute("errorMessage", "The user doesn't exist or passwords do not match");
		return "create_account";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") UserForm userForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redir) {
		
		if (!bindingResult.hasErrors()) {
			int result = userService.createUser(userForm);
			System.out.println("result" + result);
			if(result == Constants.CREATE_ACCOUNT_OK) {
				redir.addFlashAttribute("successMessage", "El usuario se ha creado satisfactoriamente.");
				return "redirect:login";
			}else if (result == Constants.CREATE_ACCOUNT_INCORRECT_PASSWORD){
				model.addAttribute("errorMessage", "La contraseña secreta para unirte a una cuenta es incorrecta");
				return "create_account";
			}else if (result == Constants.CREATE_ACCOUNT_PASSWORDS_NO_MATCH){
				model.addAttribute("errorMessage", "Las contraseñas no coinciden");
				return "create_account";
			}else if (result == Constants.CREATE_ACCOUNT_INCORRECT_ACCOUNT_ID){
				model.addAttribute("errorMessage", "El ID del dueño de la cuenta es incorrecto");
				return "create_account";
			}else if (result == Constants.CREATE_ACCOUNT_DNI_ALREADY_EXISTS){
				model.addAttribute("errorMessage", "Ya existe una cuenta registrada con ese DNI");
				return "create_account";
			}else if (result == Constants.CREATE_ACCOUNT_EMAIL_ALREADY_EXISTS){
				model.addAttribute("errorMessage", "Ya existe una cuenta registrada con ese email");
				return "create_account";
			}else {
				redir.addFlashAttribute("errorMessage", "Error desconocido al crear un usuario");
				return "redirect:login";
			}
		}
		
		redir.addFlashAttribute("errorMessage", "Unknown error creating the user");
		return "redirect:login";
	}
	
	
	
	@RequestMapping(value={"/importantInformation"}, method = RequestMethod.GET)
	public String showInformationPage(Model model) {
		return "importantInformation";
	}
	
	@RequestMapping(value={"/logOut"}, method = RequestMethod.GET)
	public String logOut(Model model, HttpSession session) {
		loginService.destroySession(session);
		return "redirect:login";
	}
	

}
