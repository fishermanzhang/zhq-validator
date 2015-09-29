package com.zhq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhq.dao.UserDao;
import com.zhq.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userService;
	/**
	 * 查询所有用户
	 */
	@RequestMapping("/index")
	public String queryUser(HttpServletRequest req){
		List<User> userList=userService.selectUsers();
		req.setAttribute("userList", userList);
		return "userList";
	}
	
	/**
	 * 转到添加界面
	 */
	@RequestMapping(value="/toAdd",method = RequestMethod.GET)
	public String toAdd(Model model){
		model.addAttribute("user", new User());
		return "add";
	}
	/**
	 * 添加用户
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Valid User user,BindingResult result){
		if(result.hasErrors()){
			model.addAttribute("user", user);
			return "add";
		}
		System.out.println(result);
		model.addAttribute("user", user);
		userService.insertUser(user);
		return "redirect:/user/index";
	}
	/**
	 * 转到修改界面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpServletRequest req,Long userId){
	   User user=userService.getUserById(userId);
		req.setAttribute("user", user);
		return "userInfo";
	}
	/**
	 * 对修改的用户进行保存
	 */
	@RequestMapping("/update")
	public String update(User user){
		userService.updateUser(user);
		return "redirect:/user/index";
	}
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	public String  delete(HttpServletRequest req,Long userId,HttpServletResponse rep){
		User user=userService.getUserById(userId);	
		userService.deleteUser(user);
		List<User> userList=userService.selectUsers();
		req.setAttribute("userList", userList);			
		return "userList";
	}
}
