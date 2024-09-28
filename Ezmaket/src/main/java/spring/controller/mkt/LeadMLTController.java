package spring.controller.mkt;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.entities.AccountDetails;
import spring.entities.Lead;

@Controller
@RequestMapping("/maketing")
public class LeadMLTController {
	@Autowired
	private LeadDao leadDao;
	@Autowired
	private AccountDao accountDao;

	@RequestMapping(value = ("/leadmkt"), method = RequestMethod.GET)
	public String Leadmktindex(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accountId = account.getAccountid();
		model.addAttribute("Account", accountDao.getAll());
		model.addAttribute("Lead", leadDao.findByAccountId(accountId));
		return "maketing/leadmkt";
	}

	@RequestMapping(value = "deleteleadmkt", method = RequestMethod.GET)
	public String deleteleadmkt(@RequestParam("LeadID") String LeadID) {
		int leadId = Integer.parseInt(LeadID); // Chuyển đổi từ String sang Integer
		leadDao.delete(leadId); // Truyền giá trị Integer vào DAO
		return "redirect:leadmkt";
	}

	// thêm mới lead
	@RequestMapping(value = "addleadmkt", method = RequestMethod.GET)
	public String addleadmkt(Model model) {
		var lead = new Lead();
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("AccountDetails", account);
		model.addAttribute("lead", lead);
		return "maketing/addleadmkt";
	}

	@RequestMapping(value = "saveleadmkt", method = RequestMethod.POST)

	public String saveleadmkt(@Valid @ModelAttribute("lead") Lead lead, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("lead", lead);
			return "maketing/addleadmkt";
		}
		if (leadDao.isPhonenumber(lead.getPhone())) {
			model.addAttribute("error", "số điện thoại đã có");
			model.addAttribute("lead", lead);
			return "maketing/addleadmkt";
		}
		try {

			leadDao.insert(lead);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("lead", lead);
			return "maketing/addleadmkt";

		}

		return "redirect:leadmkt";

	}

	// sửa lead
	@RequestMapping(value = "editleadmkt", method = RequestMethod.GET)
	public String editleadmkt(@RequestParam("LeadID") String LeadID, Model model) {
		int ID = Integer.parseInt(LeadID);
		var lead = leadDao.getByid(ID);
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    model.addAttribute("AccountDetails", account);
		model.addAttribute("lead", lead);
		return "maketing/editleadmkt";
	}

	@RequestMapping(value = "updateleadmkt", method = RequestMethod.POST)
	public String updatelead(@Valid @ModelAttribute("lead") Lead lead, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("lead", lead);
			return "maketing/editleadmkt";
		}
		try {
			leadDao.update(lead);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("lead", lead);
			return "maketing/editleadmkt";
		}
		return "redirect:leadmkt";

	}
}
