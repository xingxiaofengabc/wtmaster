package com.njwt.cms.controller;

import com.njwt.cms.entity.UserEntity;
import com.njwt.cms.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class IndexController {

    @Resource
    private UserServiceImpl userServiceImpl;
    /**
     * 首页面入口
     * @return
     */


    @RequestMapping(value={"/", "/index", "/default"})
    public String showIndex(){
        return "/index";
    }

    /**
     * Thymeleaf启动器，测试其版本。
     */
    @RequestMapping("/showFirst")
    public String showFirst(Model model){
        model.addAttribute("msg", "Thymeleaf 第一个案例");
        return "/indexFirst";
    }

    @RequestMapping("/showValues")
    public String showValues(Model model){
        model.addAttribute("username", "张三");
        return "/indexValues";
    }

    @RequestMapping("/showString")
    public String showString(Model model){
        model.addAttribute("msg", "This is Thymeleaf Demo");
        return "/indexStrings";
    }

    @RequestMapping("/showDate")
    public String showDate(Model model){
        model.addAttribute("key", new Date());
        return "indexDate";
    }

    @RequestMapping("/showLogic")
    public String showLogic(Model model){
        model.addAttribute("sex", "女");
        model.addAttribute("id","1");
        return "indexLogic";
    }

    @RequestMapping("/showLoop")
    public String showLoop(Model model){
        List<UserEntity> list = new ArrayList<>();
        list= userServiceImpl.getList();
        model.addAttribute("list", list);
        return "indexLoop";
    }

    @RequestMapping("/showLoopMap")
    public String showLoopMap(Model model){
        Map<String, UserEntity> map = new HashMap<>();
        for (UserEntity userEntity: userServiceImpl.getList()){
            map.put(userEntity.getName(), new UserEntity(userEntity.getCode(),userEntity.getName(),userEntity.getAge(),userEntity.getStatus()));
        }
       /* map.put("u1", new Users(1,"张三",20));
        map.put("u2", new Users(2,"李四",22));
        map.put("u3", new Users(3,"王五",24));*/
        model.addAttribute("map", map);

        return "indexLoopMap";
    }

    @RequestMapping("/showScope")
    public String showScope(HttpServletRequest request){
        request.setAttribute("req", "HttpServletRequest");
        request.getSession().setAttribute("sess", "HttpSession");
        request.getSession().getServletContext().setAttribute("app", "Application");
        return "indexScope";
    }

    @RequestMapping("/showURLIndex")
    public String showURLIndex(){
        return "indexUrlIndex";
    }

    @RequestMapping("/params")
    public String params(Integer id, String name){
        System.out.println("id : " + id + " ; name : " + name);
        return "indexUrlIndex";
    }


    @RequestMapping(value="/restfulParams1/{id}",method = RequestMethod.POST, produces  = "application/json;charset=UTF-8")
    public String restfulParams1(@PathVariable Integer id, String name){
        System.out.println("id : " + id + " ; name : " + name);
        return "indexUrlIndex";
    }
    @RequestMapping("/restfulParams/{id}")
    @ResponseBody
    public String restfulParams( @RequestBody Map map){
        System.out.println(map);
        System.out.println("id : " + map.get("id")+ " ; name : " + map.get("name"));
        return "indexUrlIndex";
    }

    @RequestMapping("/defaultError")
    public String testDefaultErrorPage(){

        int i = 10/0; // 除数为0

        return "testErrorPage";
    }

    @RequestMapping("/exceptionHandler")
    public String testExceptionHandler(){

        String s = null;
        s.trim(); // 空指针异常

        return "exceptionHandler";
    }

    /**
     * 如果在一个方法中需要处理多种异常，可以在@ExceptionHandler注解的value属性中定义。并可以
     * 根据代码逻辑，实现不同异常的多样化处理。
     * @ExceptionHandler - 定义异常处理方法。
     *  属性 value - 当前方法可处理的异常类型数组
     * @param e - 服务方法发生的具体异常对象
     * @param model - 数据模型对象
     * @return - 视图名称
     */
    @ExceptionHandler(value={NullPointerException.class})
    public String myHandler(Exception e, Model model){

        model.addAttribute("myMessage", "服务器正忙，请稍后重试。。。");

        return "myExceptionHandler";
    }
}
