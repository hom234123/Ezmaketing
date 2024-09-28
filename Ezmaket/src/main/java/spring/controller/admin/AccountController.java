package spring.controller.admin;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.entities.Account;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin")
public class AccountController {
	@Autowired
	private AccountDao accountDao;
	@RequestMapping("/account")
	public String Accountindex(Model model) {
		model.addAttribute("Account",accountDao.getAll());
		return"admin/account";
	}
	
	@RequestMapping(value="addaccount", method = RequestMethod.GET)
	public String addaccount(Model model) {
		var account = new Account();
		
		model.addAttribute("account", account);
		return "admin/addaccount";
		
	}
	@RequestMapping(value="saveaccount", method = RequestMethod.POST)
	public String saveaccount (@Valid@ModelAttribute("account") Account account,BindingResult result , Model model) {
		if (result.hasErrors()) {
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		if (accountDao.isId(account.getAccountid()) ) {
			model.addAttribute("error","mã này đã có");
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		if (accountDao.isEmail(account.getEmail()) ) {
			model.addAttribute("error","email đã có");
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		if (accountDao.isUsername(account.getUsername()) ) {
			model.addAttribute("error","tên đăng nhập đã có");
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		if (accountDao.isPhone(account.getPhone()) ) {
			model.addAttribute("error","số điện thoại đã có");
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		try {
			accountDao.insert(account);
		} catch (Exception e) {
			model.addAttribute("account", account);
			return "admin/addaccount";
		}
		return "redirect:account";
	}
	@RequestMapping(value="editaccount", method = RequestMethod.GET)
	public String editaccount(@RequestParam("Accountid") String Accountid, Model model) {
		var account = accountDao.getById(Accountid);		
		model.addAttribute("account", account);
		return "admin/editaccount";
		
	}
	@RequestMapping(value="updateaccount", method = RequestMethod.POST)
	public String updateaccount (@ModelAttribute("account") Account account , Model model) {
		try {
			accountDao.update(account);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("account", account);
			return "admin/editaccount";
		}
		return "redirect:account";
	}
	
	
	
	
	@RequestMapping(value = "deleteaccount", method = RequestMethod.GET)
	public String deleteAccount(@RequestParam("Accountid") String Accountid, RedirectAttributes redirectAttributes) {
		
		try {
			accountDao.delete(Accountid);
			return "redirect:account";
		} catch (NumberFormatException e ) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID tài khoản không hợp lệ.");
	           
		}catch (ConstraintViolationException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa tài khoản vì có liên kết đến tài khoản này.");
           
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa tài khoản.");
		}
		return "redirect:account";
	}
	
	
}

