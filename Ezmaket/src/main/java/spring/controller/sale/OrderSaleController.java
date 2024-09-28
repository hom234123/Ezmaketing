package spring.controller.sale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.OrderDetailsDao;
import spring.dao.OrdersDao;
import spring.dao.ProductDao;
import spring.entities.AccountDetails;
import spring.entities.OrderDetails;
import spring.entities.Orders;

@Controller
@RequestMapping("/sale")
public class OrderSaleController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrdersDao ordersDao;
	@RequestMapping(value=("/ordersale"), method = RequestMethod.GET)
	public String Ordersaleindex(Model model) {
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String accountId = account.getAccountid();

	    List<Orders> orders = ordersDao.findByAccountId(accountId);
	    model.addAttribute("Orders", orders);

	    List<OrderDetails> allOrderDetails = new ArrayList<>();
	    for (Orders order : orders) {
	        List<OrderDetails> orderDetails = orderDetailsDao.findByOrderId(order.getOrderID());
	        allOrderDetails.addAll(orderDetails);
	    }
	    model.addAttribute("OrderDetails", allOrderDetails);

	    model.addAttribute("Product", productDao.getAll());
	    model.addAttribute("Account", accountDao.getAll());
		return "sale/ordersale";
	}
	// xóa dữ liệu order
		@RequestMapping(value="deleteordersale", method = RequestMethod.GET)
		public String deleteoder (@RequestParam("OrderID") String OrderID) {
			int orderID = Integer.parseInt(OrderID);
			ordersDao.delete(orderID);
			return "redirect:ordersale";
		}
		
		@RequestMapping(value="addordersale", method = RequestMethod.GET)
		public String addorder(Model model) {
			var orders = new Orders();
			AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("AccountDetails", account);
			model.addAttribute("orders", orders);
			return "sale/addordersale";
		}
		@RequestMapping(value="saveordersale", method = RequestMethod.POST)
		public String saveorder (@ModelAttribute("orders") Orders orders, Model model) {
			if (ordersDao.isPhone(orders.getPhone())) {
				model.addAttribute("error","số điện thoại đã có");
				model.addAttribute("orders", orders);
				return "sale/addordersale";
			}
			try {
				ordersDao.insert(orders);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("orders", orders);
				return "sale/addordersale";
			}
			return "redirect:ordersale";
		}
		
		
		@RequestMapping(value="editordersale", method = RequestMethod.GET)
		public String editorder(@RequestParam("OrderID") String OrderID, Model model) {
			int id = Integer.parseInt(OrderID);
			var orders = ordersDao.getByid(id);
			AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("AccountDetails", account);
			model.addAttribute("orders", orders);
			return "sale/editordersale";
		}
		@RequestMapping(value="updateordersale", method = RequestMethod.POST)
		public String updateorder (@ModelAttribute("orders") Orders orders, Model model) {
			
			try {
				ordersDao.update(orders);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("orders", orders);
				return "sale/editordersale";
			}
			return "redirect:ordersale";
		}
		
		
		@RequestMapping(value="addorderdetailsale", method = RequestMethod.GET)
		public String addorderdetailsale(Model model) {
			var orderdetail = new OrderDetails();
			model.addAttribute("Product", productDao.getAll());
			model.addAttribute("Orders", ordersDao.getAll());
			model.addAttribute("orderDetails", orderdetail);
			return "sale/addorderdetailsale";
			
		}
		@RequestMapping(value="saveorderdetailsale", method = RequestMethod.POST)
		public String saveorderdetail (@ModelAttribute("orderDetails") OrderDetails orderDetails, Model model) {
			try {
				orderDetailsDao.insert(orderDetails);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("orderDetails", orderDetails);
				return "sale/addorderdetailsale";
			}
			return "redirect:ordersale";
		}
		@RequestMapping(value="editorderdetailsale", method = RequestMethod.GET)
		public String editorderdetailsale(@RequestParam("OrderDetailID") String OrderDetailID, Model model) {
			int oD = Integer.parseInt(OrderDetailID);
			var orderDetails = orderDetailsDao.getByid(oD);
			model.addAttribute("Product", productDao.getAll());
			model.addAttribute("Orders", ordersDao.getAll());
			model.addAttribute("orderDetails", orderDetails);
			return "sale/editorderdetailsale";
		}
		@RequestMapping(value="updateorderdetailsale", method = RequestMethod.POST)
		public String updateorderdetailsale (@ModelAttribute("orderDetails") OrderDetails orderDetails, Model model) {
			try {
				orderDetailsDao.update(orderDetails);
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("orderDetails", orderDetails);
				return "sale/editorderdetailsale";
			}
			return "redirect:ordersale";
		}
		// xóa dữ liệu orderdetail
		@RequestMapping(value="deleteorderdetailsale", method = RequestMethod.GET)
		public String deleteoderdetailsale (@RequestParam("OrderDetailID") String OrderDetailID) {
			int orderDetailID = Integer.parseInt(OrderDetailID);
			orderDetailsDao.delete(orderDetailID);
			return "redirect:ordersale";
		}
}
