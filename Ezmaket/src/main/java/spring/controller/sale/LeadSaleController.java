package spring.controller.sale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.entities.Account;
import spring.entities.Lead;

@Controller
@RequestMapping("/sale")
public class LeadSaleController {
	@Autowired
	private LeadDao leadDao;
	@Autowired
	private AccountDao accountDao;
	@RequestMapping(value = ("/leadsale"), method = RequestMethod.GET)
	public String Leadsaleindex(Model model) {
		model.addAttribute("Lead", leadDao.getAll());
		model.addAttribute("Account", accountDao.getAll());
		return "sale/leadsale";
	}
	// sửa lead
		@RequestMapping(value = "editleadsale", method = RequestMethod.GET)
		public String editlead(@RequestParam("LeadID") String LeadID, Model model) {
			int ID = Integer.parseInt(LeadID);
			var lead = leadDao.getByid(ID);
			Account account = accountDao.getById(String.valueOf(lead.getAccountid()));
			model.addAttribute("accountFullname", account.getFullname());
			model.addAttribute("AccountList", accountDao.getAll());
			model.addAttribute("lead",lead );
			return"sale/editleadsale";
		}
		
		@RequestMapping(value = "updateleadsale",  method = RequestMethod.POST)
		public String updatelead (@Valid@ModelAttribute("lead") Lead lead,BindingResult result, Model model) {
			if (result.hasErrors()) {
				model.addAttribute("lead", lead);
				return "sale/editleadsale";
			}
			try {
				leadDao.update(lead);
			} catch (Exception ex) {
				model.addAttribute("error", ex.getMessage());
				model.addAttribute("lead", lead);
				return "sale/editleadsale";
			}
			return "redirect:leadsale";
			
		}
}
