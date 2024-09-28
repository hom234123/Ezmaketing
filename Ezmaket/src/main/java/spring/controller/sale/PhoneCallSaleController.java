package spring.controller.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.dao.PhoneCallDao;
import spring.entities.AccountDetails;
import spring.entities.PhoneCall;

@Controller
@RequestMapping("/sale")
public class PhoneCallSaleController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private LeadDao leadDao;
	@Autowired
	private PhoneCallDao phoneCallDao;

	@RequestMapping(value = ("/phonecallsale"), method = RequestMethod.GET)
	public String Phonecallsaleindex(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accountId = account.getAccountid();
		model.addAttribute("Account", accountDao.getAll());
		model.addAttribute("PhoneCall", phoneCallDao.findByAccountId(accountId));
		return "sale/phonecallsale";

	}

	@RequestMapping(value = "deletephonesale", method = RequestMethod.GET)
	public String deletephonesale(@RequestParam("CallID") String CallID) {
		int callID = Integer.parseInt(CallID); // Chuyển đổi từ String sang Integer
		phoneCallDao.delete(callID); // Truyền giá trị Integer vào DAO
		return "redirect:phonecallsale";
	}

	@RequestMapping(value = "addphonecallsale", method = RequestMethod.GET)
	public String addphonecallsale(Model model) {
		var phonecall = new PhoneCall();
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("AccountDetails", account);
		model.addAttribute("LeadList", leadDao.getAll());		
		model.addAttribute("phonecall", phonecall);
		return "sale/addphonecallsale";
	}

	@RequestMapping(value = "savephonecallsale", method = RequestMethod.POST)

	public String savephonecallsale(@ModelAttribute("phonecall") PhoneCall phonecall, Model model) {
		if (phoneCallDao.isLeadID(phonecall.getLeadID()) ) {
			model.addAttribute("error","mã này đã lên");
			model.addAttribute("phonecall", phonecall);
			return "sale/addphonecallsale";
		}
		try {
			phoneCallDao.insert(phonecall);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("phonecall", phonecall);
			return "sale/addphonecallsale";

		}

		return "redirect:phonecallsale";

	}

	@RequestMapping(value = "editphonecallsale", method = RequestMethod.GET)
	public String editphonecall(@RequestParam("CallID") String CallID, Model model) {
		int Id = Integer.parseInt(CallID);
		var phonecall = phoneCallDao.getByid(Id);
		model.addAttribute("LeadList", leadDao.getAll());
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("AccountDetails", account);
		model.addAttribute("phonecall", phonecall);
		return "sale/editphonecallsale";
	}

	@RequestMapping(value = "updatephonecallsale", method = RequestMethod.POST)
	public String updatephonecallsale(@ModelAttribute("phonecall") PhoneCall phonecall, Model model) {
		try {
			phoneCallDao.update(phonecall);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("Phonecall", phonecall);
			return "sale/editphonecallsale";

		}

		return "redirect:phonecallsale";

	}
}
