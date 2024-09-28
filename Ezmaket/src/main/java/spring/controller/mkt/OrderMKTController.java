package spring.controller.mkt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.dao.AccountDao;
import spring.dao.OrderDetailsDao;
import spring.dao.OrdersDao;
import spring.dao.ProductDao;
import spring.entities.AccountDetails;
import spring.entities.Orders;

@Controller
@RequestMapping("/maketing")
public class OrderMKTController {
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Autowired
	private ProductDao productDao;
	@RequestMapping(value = ("/ordermkt"), method = RequestMethod.GET)
	public String Ordermktindex(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accountId = account.getAccountid();
        
        // Lấy danh sách đơn hàng dựa trên accountId
        List<Orders> ordersList = ordersDao.findOrdersByAccountId(accountId);
		model.addAttribute("Account",accountDao.getAll());
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("OrderDetails", orderDetailsDao.getAll());
		model.addAttribute("Product", productDao.getAll());
		return "maketing/ordermkt";
	}
}
