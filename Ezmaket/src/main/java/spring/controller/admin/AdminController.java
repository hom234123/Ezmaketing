package spring.controller.admin;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.dao.OrdersDao;
import spring.dao.PhoneCallDao;
import spring.dao.ProductDao;
import spring.entities.AccountDetails;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private LeadDao leadDao;
	
	@Autowired
	private PhoneCallDao phoneCallDao;

	@RequestMapping("/")
	public String index(Model model) {
		Long totalAccount = accountDao.countTotalAccount();
		model.addAttribute("totalAccount", totalAccount);
		
		Long totalLead = leadDao.countTotalPhone();
		model.addAttribute("totalLead", totalLead);
		
		Long totalphonestatus = leadDao.phonecallstatus();
		model.addAttribute("totalphonestatus", totalphonestatus);
		
		Long totalProduct = productDao.countTotalProduct();
		model.addAttribute("totalProduct", totalProduct);
		
		Long totalOrders = ordersDao.countTotalOrders();	
		model.addAttribute("totalOrders", totalOrders);
		
		Long orderStatus = ordersDao.orderStatus();
		model.addAttribute("orderStatus", orderStatus);
		
		Long totalPhonecall = phoneCallDao.totalPhonecall();
		model.addAttribute("totalPhonecall", totalPhonecall);
		
		Double totalAmount = ordersDao.sumTotalAmount();	    
	    // Định dạng số tiền trước khi đưa vào model
	    String formattedTotalAmount = formatTotalAmount(totalAmount);
	    model.addAttribute("totalamount", formattedTotalAmount);
		
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("account", account);		
		model.addAttribute("Account", accountDao.getAll());
		model.addAttribute("Orders", ordersDao.getAll());
		model.addAttribute("Lead", leadDao.getAll());
		return "admin/home";
	}
	public String formatTotalAmount(Double totalAmount) {
	    DecimalFormat decimalFormat = new DecimalFormat("#,###"); 
	    return decimalFormat.format(totalAmount);
	}

}
