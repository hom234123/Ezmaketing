package spring.controller.admin;

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
import spring.entities.Lead;

@Controller
@RequestMapping("/admin")
public class LeadController {
	@Autowired
	private LeadDao leadDao;
	@Autowired
	private AccountDao accountDao;

	@RequestMapping(value =("/lead"), method = RequestMethod.GET)
	public String Leadindex(Model model) {
		model.addAttribute("Account", accountDao.getAll());
		model.addAttribute("Lead", leadDao.getAll());
		return "admin/lead";
	}

	@RequestMapping(value = "deletelead", method = RequestMethod.GET)
	public String deletelead(@RequestParam("LeadID") String LeadID) {
		int leadId = Integer.parseInt(LeadID); // Chuyển đổi từ String sang Integer
		leadDao.delete(leadId); // Truyền giá trị Integer vào DAO
		return "redirect:lead";
	}
  // thêm mới lead
	@RequestMapping(value = "addlead", method = RequestMethod.GET)
	public String addlead(Model model) {
		var lead = new Lead();
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("lead", lead);
		return "admin/addlead";
	}

	@RequestMapping(value = "savelead", method = RequestMethod.POST)

	public String savelead(@Valid@ModelAttribute("lead") Lead lead,BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("lead", lead);
			return "admin/addlead";
		}
		if (leadDao.isPhonenumber(lead.getPhone()) ) {
			model.addAttribute("error","số điện thoại đã có");
			model.addAttribute("lead", lead);
			return "admin/addlead";
		}
		try {
			
			leadDao.insert(lead);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("lead", lead);
			return "admin/addlead";

		}

		return "redirect:lead";

	}
	// sửa lead
	@RequestMapping(value = "editlead", method = RequestMethod.GET)
	public String editlead(@RequestParam("LeadID") String LeadID, Model model) {
		int ID = Integer.parseInt(LeadID);
		var lead = leadDao.getByid(ID);
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("lead",lead );
		return"admin/editlead";
	}
	
	@RequestMapping(value = "updatelead",  method = RequestMethod.POST)
	public String updatelead (@Valid@ModelAttribute("lead") Lead lead,BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("lead", lead);
			return "admin/editlead";
		}
		try {
			leadDao.update(lead);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("lead", lead);
			return "admin/editlead";
		}
		return "redirect:lead";
		
	}
	
}
