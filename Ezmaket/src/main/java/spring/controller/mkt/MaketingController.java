package spring.controller.mkt;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.AccountDao;
import spring.dao.LeadDao;
import spring.dao.OrdersDao;
import spring.entities.AccountDetails;
import spring.entities.Orders;

@Controller
@RequestMapping("/maketing")
public class MaketingController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private LeadDao leadDao;

	@RequestMapping("/")
	public String index(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accountId = account.getAccountid();
		
		List<Orders> ordersList = ordersDao.findOrdersByAccountId(accountId);
		model.addAttribute("ordersList", ordersList);
		
		model.addAttribute("Account", accountDao.getAll());
		model.addAttribute("Orders", ordersDao.getAll());
		
		Long totalLeadAc = leadDao.countPhoneAc(accountId);
        model.addAttribute("totalLeadAc", totalLeadAc);
        
        Long totalleadsale = leadDao.countPhonesale(accountId);
        model.addAttribute("totalleadsale", totalleadsale);
        
        model.addAttribute("Lead", leadDao.findByAccountId(accountId));
     // Đếm tổng số đơn hàng có trạng thái 1
        Long totalOrders1 = ordersDao.countPhoneOrStatus(accountId, 1);  
        model.addAttribute("totalOrders1", totalOrders1);

        Long totalOrders1Amount = ordersDao.sumTotalAmountOrs(accountId, 1);
        String formattedTotalAmount = formatTotalAmount(totalOrders1Amount);
        model.addAttribute("totalOrders1Amount", formattedTotalAmount);
        
		model.addAttribute("account", account);
		return "maketing/home";
	}
	public String formatTotalAmount(Long totalOrders1Amount) {
	    DecimalFormat decimalFormat = new DecimalFormat("#,###"); 
	    return decimalFormat.format(totalOrders1Amount);
	}
}
