package spring.controller.admin;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.AccountDao;
import spring.dao.AccountsRoleDao;
import spring.dao.CategoryDao;
import spring.dao.ProductDao;
import spring.dao.RolesDao;
import spring.entities.Account;
import spring.entities.AccountRole;
import spring.entities.Category;
import spring.entities.Product;
import spring.entities.Role;

@Controller
@RequestMapping("/admin")
public class WarehouseController {
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private AccountsRoleDao accountsRoleDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private AccountDao accountDao;

	@RequestMapping("/warehouse")
	public String warehouseindex(Model model) {
		model.addAttribute("Role", rolesDao.getAll());
		model.addAttribute("AccountRole", accountsRoleDao.getAll());
		model.addAttribute("Category", categoryDao.getAll());
		model.addAttribute("Product", productDao.getAll());
		return "admin/warehouse";
	}

	// Product
	@RequestMapping(value = "addproduct", method = RequestMethod.GET)
	public String addproduct(Model model) {
		var product = new Product();
		model.addAttribute("Categorylist", categoryDao.getAll());
		model.addAttribute("product", product);
		return "admin/addproduct";

	}

	@RequestMapping(value = "saveproduct", method = RequestMethod.POST)
	public String saveproduct(@ModelAttribute("product") Product product, Model model) {
		try {
			productDao.insert(product);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("product", product);
			return "admin/addproduct";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "editproduct", method = RequestMethod.GET)
	public String editproduct(@RequestParam("ProductID") String ProductID, Model model) {
		int Id = Integer.parseInt(ProductID);
		var product = productDao.getByid(Id);
		model.addAttribute("Categorylist", categoryDao.getAll());
		model.addAttribute("product", product);
		return "admin/editproduct";

	}

	@RequestMapping(value = "updateproduct", method = RequestMethod.POST)
	public String updateproduct(@ModelAttribute("product") Product product, Model model) {
		try {
			productDao.update(product);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("product", product);
			return "admin/editproduct";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "deleteproduct", method = RequestMethod.GET)
	public String deleteproduct(@RequestParam("ProductID") String ProductID) {
		int productID = Integer.parseInt(ProductID);
		productDao.delete(productID);
		return "redirect:warehouse";
	}

	// Category
	@RequestMapping(value = "addcategory", method = RequestMethod.GET)
	public String addcategory(Model model) {
		var category = new Category();
		model.addAttribute("category", category);
		return "admin/addcategory";
	}

	@RequestMapping(value = "savecategory", method = RequestMethod.POST)
	public String savecategory(@ModelAttribute("category") Category category, Model model) {
		try {
			categoryDao.insert(category);
		} catch (Exception e) {
			System.out.print("hello");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("category", category);
			return "admin/addcategory";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "editcategory", method = RequestMethod.GET)
	public String category(@RequestParam("CategoryID") String CategoryID, Model model) {
		int ct = Integer.parseInt(CategoryID);
		var category = categoryDao.getById(ct);
		model.addAttribute("category", category);
		return "admin/editcategory";
	}

	@RequestMapping(value = "updatecategory", method = RequestMethod.POST)
	public String updatecategory(@ModelAttribute("category") Category category, Model model) {

		try {
			categoryDao.update(category);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("category", category);
			return "admin/editcategory";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "deletecategory", method = RequestMethod.GET)
	public String deletectegory(@RequestParam("CategoryID") String CategoryID) {
		int categoryID = Integer.parseInt(CategoryID);
		categoryDao.delete(categoryID);
		return "redirect:warehouse";
	}

	// Role
	@RequestMapping(value = "addrole", method = RequestMethod.GET)
	public String addrole(Model model) {
		var role = new Role();
		model.addAttribute("role", role);
		return "admin/addrole";
	}

	@RequestMapping(value = "saverole", method = RequestMethod.POST)
	public String saverole(@ModelAttribute("role") Role role, Model model) {
		try {
			rolesDao.insert(role);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("role", role);
			return "admin/addrole";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "editrole", method = RequestMethod.GET)
	public String editrole(@RequestParam("Roleid") String Roleid, Model model) {
		int rl = Integer.parseInt(Roleid);
		var role = rolesDao.getByid(rl);
		model.addAttribute("role", role);
		return "admin/editrole";
	}

	@RequestMapping(value = "updaterole", method = RequestMethod.POST)
	public String updaterole(@ModelAttribute("role") Role role, Model model) {

		try {
			rolesDao.update(role);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("role", role);
			return "admin/editrole";
		}
		return "redirect:warehouse";
	}

	@RequestMapping(value = "deleterole", method = RequestMethod.GET)
	public String deleterole(@RequestParam("Roleid") String Roleid) {
		Long roleid = Long.parseLong(Roleid);
		rolesDao.delete(roleid);
		return "redirect:warehouse";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String Roleid) {
				Role role = rolesDao.getByid(Integer.valueOf(Roleid));
				setValue(role);
			}
		});

		binder.registerCustomEditor(Account.class, new PropertyEditorSupport() {
			public void setAsText(String Accountid) {
				Account account = accountDao.getById(Accountid);
				setValue(account);
			}
		});
	}

	// accountroles
	@RequestMapping(value = "addaccountrole", method = RequestMethod.GET)
	public String addaccountrole(Model model) {
		var accountrole = new AccountRole();
		model.addAttribute("RoleList", rolesDao.getAll());
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("accountrole", accountrole);
		return "admin/addaccountrole";

	}

	@RequestMapping(value = "saveaccountrole", method = RequestMethod.POST)
	public String saveaccountrole(@ModelAttribute("accountrole") AccountRole accountRole, Model model) {
		try {
			accountsRoleDao.insert(accountRole);
		} catch (Exception e) {
			model.addAttribute("RoleList", rolesDao.getAll());
			model.addAttribute("AccountList", accountDao.getAll());
			model.addAttribute("accountrole", accountRole);
			return "admin/addaccountrole";
		}
		return "redirect:warehouse";

	}

	@RequestMapping(value = "editaccountrole", method = RequestMethod.GET)
	public String editaccountrole(@RequestParam("Id") Long id, Model model) {
		var accountrole = accountsRoleDao.getById(id);
		model.addAttribute("RoleList", rolesDao.getAll());
		model.addAttribute("AccountList", accountDao.getAll());
		model.addAttribute("accountrole", accountrole);
		return "admin/editaccountrole";
	}
	
	@RequestMapping (value= "updateaccountrole", method = RequestMethod.POST)
	public String updateaccountrole (@ModelAttribute("accountrole") AccountRole accountRole, Model model) {
		try {
			accountsRoleDao.update(accountRole);
		} catch (Exception e) {
			model.addAttribute("RoleList", rolesDao.getAll());
			model.addAttribute("AccountList", accountDao.getAll());
			model.addAttribute("accountrole", accountRole);
			return "admin/editaccountrole";
		}
		return "redirect:warehouse";
	}
}
