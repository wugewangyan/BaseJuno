package com.juno.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.juno.interceptor.PageInterceptor.Page;
import com.juno.model.Shop;
import com.juno.service.IShopService;


@Controller
@RequestMapping(value = "/shop/*")
@SessionAttributes("shop")
public class ShopController {

	@Resource(name = "shopServiceImpl")
	private IShopService shopService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(Model model){
		Shop shop = new Shop();
		model.addAttribute("shop", shop);
		return "shop/add_shop";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String post(@ModelAttribute("shop") Shop shop, BindingResult result, SessionStatus status, Model model){
		if(shop.getShopId() != null && !"".equals(shop.getShopId())){
			this.shopService.update(shop);
		}else{
			this.shopService.insert(shop);
		}
		
		status.setComplete();
		return "redirect:list";
	}
	
	@RequestMapping("list")
	public String list(Model model){
		Page<Shop> page = this.shopService.listAll(new Shop());
		model.addAttribute("list", page.getResult());
		return "shop/list_shop";
	}
	
	@RequestMapping("update")
	public String getByID(@RequestParam(value = "shopId") String shopId, Model model){
		Shop shop = this.shopService.get(shopId);
		model.addAttribute("shop", shop);
		return "shop/add_shop";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam(value = "shopId") String shopId){
		this.shopService.delete(shopId);
		return "shop/list_shop";
	}
	
}
