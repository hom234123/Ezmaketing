package spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
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
import spring.entities.OrderDetails;
import spring.entities.Orders;

@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrdersDao ordersDao;
	@RequestMapping("/order")
	public String Orderindex(Model model) {
		model.addAttribute("Product", productDao.getAll());
		model.addAttribute("Account",accountDao.getAll());
		model.addAttribute("OrderDetails", orderDetailsDao.getAll());
		model.addAttribute("Orders", ordersDao.getAll());
		return "admin/order";
	}
	@RequestMapping(value="addorder", method = RequestMethod.GET)
	public String addorder(Model model) {
		var orders = new Orders();
		model.addAttribute("AccountList",accountDao.getAll());
		model.addAttribute("orders", orders);
		return "admin/addorder";
	}
	@RequestMapping(value="saveorder", method = RequestMethod.POST)
	public String saveorder (@ModelAttribute("orders") Orders orders, Model model) {
		if (ordersDao.isPhone(orders.getPhone())) {
			model.addAttribute("error","số điện thoại đã có");
			model.addAttribute("orders", orders);
			return "admin/addorder";
		}
		try {
			ordersDao.insert(orders);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("orders", orders);
			return "admin/addorder";
		}
		return "redirect:order";
	}
	@RequestMapping(value="editorder", method = RequestMethod.GET)
	public String editorder(@RequestParam("OrderID") String OrderID, Model model) {
		int id = Integer.parseInt(OrderID);
		var orders = ordersDao.getByid(id);
		model.addAttribute("AccountList",accountDao.getAll());
		model.addAttribute("orders", orders);
		return "admin/editorder";
	}
	@RequestMapping(value="updateorder", method = RequestMethod.POST)
	public String updateorder (@ModelAttribute("orders") Orders orders, Model model) {
		try {
			ordersDao.update(orders);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("orders", orders);
			return "admin/editorder";
		}
		return "redirect:order";
	}
	
	// xóa dữ liệu order
	@RequestMapping(value="deleteorder", method = RequestMethod.GET)
	public String deleteoder (@RequestParam("OrderID") String OrderID) {
		int orderID = Integer.parseInt(OrderID);
		ordersDao.delete(orderID);
		return "redirect:order";
	}
	
	@RequestMapping(value="addorderdetail", method = RequestMethod.GET)
	public String addorderdetail(Model model) {
		var orderdetail = new OrderDetails();
		model.addAttribute("Product", productDao.getAll());
		model.addAttribute("Orders", ordersDao.getAll());
		model.addAttribute("orderDetails", orderdetail);
		return "admin/addorderdetail";
		
	}
	@RequestMapping(value="saveorderdetail", method = RequestMethod.POST)
	public String saveorderdetail (@ModelAttribute("orderDetails") OrderDetails orderDetails, Model model) {
		try {
			orderDetailsDao.insert(orderDetails);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("orderDetails", orderDetails);
			return "admin/addorder";
		}
		return "redirect:order";
	}
	@RequestMapping(value="editorderdetail", method = RequestMethod.GET)
	public String editorderdetail(@RequestParam("OrderDetailID") String OrderDetailID, Model model) {
		int oD = Integer.parseInt(OrderDetailID);
		var orderDetails = orderDetailsDao.getByid(oD);
		model.addAttribute("Product", productDao.getAll());
		model.addAttribute("Orders", ordersDao.getAll());
		model.addAttribute("orderDetails", orderDetails);
		return "admin/editorderdetail";
	}
	@RequestMapping(value="updateorderdetail", method = RequestMethod.POST)
	public String updateorderdetail (@ModelAttribute("orderDetails") OrderDetails orderDetails, Model model) {
		try {
			orderDetailsDao.update(orderDetails);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("orderDetails", orderDetails);
			return "admin/editorderdetail";
		}
		return "redirect:order";
	}
	// xóa dữ liệu orderdetail
	@RequestMapping(value="deleteorderdetail", method = RequestMethod.GET)
	public String deleteoderdetail (@RequestParam("OrderDetailID") String OrderDetailID) {
		int orderDetailID = Integer.parseInt(OrderDetailID);
		orderDetailsDao.delete(orderDetailID);
		return "redirect:order";
	}
}
