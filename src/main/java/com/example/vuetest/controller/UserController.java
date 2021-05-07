package com.example.vuetest.controller;

import com.example.vuetest.model.User;
import com.example.vuetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author zwzhang5
 * @description
 * @date Created on 2021/3/29 11:44
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUserList")
    public Map<String, Object> getUserList(Model model, Integer currentPage, Integer pageSize) {
        /*
        调用业务层方法获取商品信息
         */
        int startPage = (currentPage - 1) * pageSize;
        List<User> list = userService.getUserList(startPage, pageSize);
        /*
        获取用户的记录数总数
         */
        int total = userService.getUserCount();
        /*
        获取当前页
         */
        int pageIndex = currentPage;
        /*
        将数据存储到请求作用域
         */
        model.addAttribute("list", list);
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", list);
        map.put("total", total);
        map.put("currentPage", pageIndex);
        int jiejuechongtu = 900;
        System.out.println(jiejuechongtu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.POST)
    public String deleteUserById(int userId) {
        boolean flag = userService.deleteUserById(userId);
        return "redirect:/user/getUserList";
    }

    @RequestMapping("/selectUserById/{id}")
    public String selectUserById(@PathVariable Integer id, Model model) {
        User user = userService.selectUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserById", method = RequestMethod.POST)
    public String updateUserById(User user) {
        boolean flag = userService.updateUserById(user);
        return "redirect:/user/getUserList";
    }

    @RequestMapping("/insertUserUI")
    public String insertUserUI(User user) {
        return "insertUser";
    }

    @ResponseBody
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public String insertUser(User user) {
        boolean flag = userService.insertUser(user);
        return "redirect:/user/getUserList";
    }

    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile){
        return FileUpload.upload(multipartFile).replaceAll("\\\\", "/");
    }

    @ResponseBody
    @RequestMapping(value = "/getUploadFileList")
    public List<Object> getUploadFileList(){
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        File file=new File(staticPath+"/img");

        File[] list = file.listFiles();
        List<Object> list1 = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name",list[i].getName());
            map.put("url",file+list[i].getName());
            list1.add(map);
        }
        return list1;
    }
    @RequestMapping("/downloadFile")
    public void downloadFile(@RequestParam String fileName, HttpServletResponse response) throws Exception {
        FileDownload.download(fileName, response);
    }
}
