package com.unileon.insoII.mgb.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.form.model.OperationForm;
import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.OperationService;
import com.unileon.insoII.mgb.service.TransferService;
import com.unileon.insoII.mgb.service.CardService;
import com.unileon.insoII.mgb.service.UserService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;

	@Autowired
	TransferService transferService;
	@Autowired
	OperationService operationService;
	@Autowired
	CardService cardService;
	
	
	@PostMapping(value= {"/profile"})
	public String updateFirstLogin(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		userService.markFirstLoginAsResolved(user, session);
		return "redirect:dashboard";
	}
	
	@RequestMapping(value= {"/profile"}, method = RequestMethod.GET)
	public String showDashboard(ModelMap model, HttpSession session) {
		//Sacamos el usuario de la sesion
		System.out.println("ENTRO");
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			System.out.println("sesion muerta, vamos al login");
			return "redirect:login";
		}
		//Recargamos la sesion con nuevos datos o si se hace f5
		user = userService.getUserById(user.getEmail());
		loginService.createSession(user, session);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(user.isFirstLogin())
			user = userService.markFirstLoginAsResolved(user, session);*/
		
		System.out.println(user.toString());
		//Añadimos el usuario al model
		model.put("user", user);
		//Obtenemos la lista de cuentas del usuario
		List<Account> accountList = user.getListOfAccounts();
		//System.out.println("A:" + accountList.get(0).getBalance());
		model.put("accounts", accountList);
		model.put("editUser", new UserForm());
		
		return "profile";
	}
	@RequestMapping(value="/editUser", method=RequestMethod.POST)
	public String editUser(@ModelAttribute("editUser") UserForm userForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redir, HttpSession session) {
		if(!bindingResult.hasErrors()) {
			
			System.out.println("Entro");
			User user = (User) session.getAttribute("user");
			if(user==null) {
				redir.addFlashAttribute("errorMessage", "El usuario no está logeado o se ha perdido la sesion");
				return "redirect:login";			
			}
			int result = userService.editUser(userForm,user);
			
			System.out.println("Result: "+ result);
			
			if (result==Constants.EDIT_USER_OK)
				redir.addFlashAttribute("successMessage", "Perfil editado correctamente");
			//TODO Añadir errores
			if (result==Constants.EDIT_PASSWORD_EMPTY_OLD)
				redir.addFlashAttribute("errorMessage", "No has introducido la contraseña actual al intentar modificar la contraseña");
			if (result==Constants.EDIT_PASSWORD_EMPTY)
				redir.addFlashAttribute("errorMessage", "No has introducido la contraseña nueva al intentar modificar la contraseña");
			if (result==Constants.EDIT_PASSWORD_NOT_MATCH)
				redir.addFlashAttribute("errorMessage", "La contraseñas no coinciden");
			if (result==Constants.EDIT_PASSWORD_OLD_INCORRECT)
				redir.addFlashAttribute("errorMessage", "La contraseña no coincide con la actual");
			
			return "redirect:profile";
		}
		System.out.println("No-Entro");
		return "redirect:profile";
	}
	@RequestMapping(value="/deleteUser", method=RequestMethod.GET)
	public String deleteAccount(ModelMap model, @RequestParam int userId, HttpSession session,  RedirectAttributes redir) {
		
		User user = userService.getUser(userId);
		userService.deleteUser(user);
		redir.addFlashAttribute("successMessage", "El usuario con DNI "+ user.getDni() + " ha sido borrado correctamente");
		
		return "redirect:login";
	}
}
