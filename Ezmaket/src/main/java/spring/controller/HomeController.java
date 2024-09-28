package spring.controller;

import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entities.AccountDetails;

@Controller
public class HomeController {
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String Login(@Nullable @RequestParam(value = "error") String error, Model model) {
		if (error != null) {
			model.addAttribute("msg", "lỗi đăng nhập");
		}
		return "login";
	}

	@RequestMapping(value = "/checkrole")
	public String checkRole() {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//System.out.println("Roles: " + account.getAuthorities());
		for (var g : account.getAuthorities()) {
			if (g.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/admin/";
			}
			if (g.getAuthority().equals("ROLE_MAKETING")) {
				return "redirect:/maketing/";
			}
			if (g.getAuthority().equals("ROLE_SALE")) {
				return "redirect:/sale/";
			}
		}
		return "403";
	}

	@RequestMapping("/logoutSuccess")
	public String Logout(Model model) {
		model.addAttribute("msg", "Đăng xuất thành công");
		return "login";
	}

	@RequestMapping("/403")
	public String accessDenied(Model model) {
		model.addAttribute("msg", "BẠN KHÔNG CÓ QUYỀN TRUY CẬP VÀO TRANG NÀY");
		return "403";
	}
	@RequestMapping("/forgot")
	public String forgot(Model model) {
		return "forgot";
	}
}
