package spring.controller.sale;

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
import spring.entities.AccountDetails;

@Controller
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private PhoneCallDao phoneCallDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private LeadDao leadDao;
	@RequestMapping("/")
	public String index(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accountId = account.getAccountid();
		
		//tính tổng doanh thu
		Double totalAmount = ordersDao.findTotalAmountByAccountId(accountId);
		String formattedTotalAmount = formatTotalAmount(totalAmount);
		model.addAttribute("totalAmount", formattedTotalAmount);
		
		model.addAttribute("Account", accountDao.getAll());
		
		//tính tổng đơn hàng theo tài khoản
		Long totalOrders = ordersDao.countOrdersByAccountId(accountId);
		model.addAttribute("totalOrders", totalOrders);
		//tính tổng số đt
		Long totalLead = leadDao.countTotalPhone();
        model.addAttribute("totalLead", totalLead);
        //tổng số cuộc gọi
        Long totalPhonecall = phoneCallDao.countTotalPhonecall(accountId);
        model.addAttribute("totalPhonecall", totalPhonecall);
        
		model.addAttribute("Orders",ordersDao.findByAccountId(accountId));
		model.addAttribute("Lead", leadDao.getAll());
		
		model.addAttribute("account", account);
		return "sale/home";
	}
	public String formatTotalAmount(Double totalAmount) {
	    DecimalFormat decimalFormat = new DecimalFormat("#,###"); 
	    return decimalFormat.format(totalAmount);
	}
}
