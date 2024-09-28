package spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.dao.PhoneCallDao;
import spring.entities.PhoneCall;

@Controller
@RequestMapping("/admin")
public class PhonecallController {
	@Autowired
	private LeadDao leadDao;
	@Autowired
	private PhoneCallDao phoneCallDao;
	@Autowired
	private AccountDao accountDao;
	@RequestMapping("/phonecall")
	public String PhoneIndex(Model model) {
		model.addAttribute("Account",accountDao.getAll());
		model.addAttribute("PhoneCall", phoneCallDao.getAll());
		return "admin/phonecall";
	}
	
	@RequestMapping(value ="deletephone", method = RequestMethod.GET)
	public String deletephone(@RequestParam("CallID") String CallID) {
		 int callID = Integer.parseInt(CallID);  // Chuyển đổi từ String sang Integer
		 phoneCallDao.delete(callID);  // Truyền giá trị Integer vào DAO
		    return "redirect:phonecall";
	}
	
	@RequestMapping(value = "addphonecall", method = RequestMethod.GET)
	public String addphonecall(Model model) {
		var phonecall = new PhoneCall();
		model.addAttribute("LeadList", leadDao.getAll());
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("phonecall", phonecall);
		return "admin/addphonecall";
	}
	
	@RequestMapping(value = "savephonecall", method = RequestMethod.POST)

	public String savephonecall(@ModelAttribute("phonecall") PhoneCall phonecall, Model model) {
		try {
			phoneCallDao.insert(phonecall);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("phonecall", phonecall);
			return "admin/addphonecall";

		}

		return "redirect:phonecall";

	}
	
	@RequestMapping (value="editphonecall", method = RequestMethod.GET)
	public String editphonecall (@RequestParam("CallID") String CallID, Model model) {
		int Id = Integer.parseInt(CallID);
		var phonecall = phoneCallDao.getByid(Id);
		model.addAttribute("LeadList", leadDao.getAll());
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("phonecall", phonecall);
		return"admin/editphonecall";
	}
	
	@RequestMapping (value="updatephonecall", method = RequestMethod.POST)
	public String updatephonecall(@ModelAttribute("phonecall") PhoneCall phonecall, Model model) {
		try {
			phoneCallDao.update(phonecall);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("Phonecall", phonecall);
			return "admin/editphonecall";

		}

		return "redirect:phonecall";

	}
	
}
