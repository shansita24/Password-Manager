package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@Controller
public class controller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String username;
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
    try{
        String sql = "SELECT COUNT(*) FROM accounts WHERE username = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);

        if (count == 1) {
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            this.username=username;
            return "redirect:/main";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "error1";
    }
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("fullname") String Name,
                     @RequestParam("email") String email,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password) {
    try
    {
        String sql = "INSERT INTO accounts (Name, email, username, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, Name, email, username, password);
        this.username=username;
        return "redirect:/main" ;
    } catch (Exception e) {
        e.printStackTrace();
        return "error1";
    }
    }


    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("username", username);
        return "main";
    }

    @GetMapping("/passwordgenerator")
    public String show() {
        return "passwordgenerator";
    }

    @GetMapping("/passwordmanager")
    public String passwordManager(Model model) {
        model.addAttribute("username", username);
        String sql = "SELECT url, pwd FROM manager WHERE username = ?";
        List<Map<String, Object>> passwordEntries = jdbcTemplate.queryForList(sql, username);
        model.addAttribute("passwordEntries", passwordEntries);
        return "passwordmanager"; 
    }

    @GetMapping("/pwdstrengthchecker")
    public String getMethodName() {
        return "pwdstrengthchecker";
    }
    
    @PostMapping("/addEntry")
    public String addEntry(@RequestParam("url") String url,
                       @RequestParam("password") String password,
                       Model model) {
    try {
        String insertSql = "INSERT INTO manager (username, url, pwd) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, username, url, password);
        String selectSql = "SELECT url, pwd FROM manager WHERE username = ?";
        List<Map<String, Object>> passwordEntries = jdbcTemplate.queryForList(selectSql, username);
        model.addAttribute("passwordEntries", passwordEntries);
        
        return "passwordmanager"; 
    } catch (Exception e) {
        e.printStackTrace();
        return "error1";
    }
    }
    @PostMapping("/deleteEntry")
    public String deleteEntry(@RequestParam("url") String url, Model model) {
    try {
        String deleteSql = "DELETE FROM manager WHERE username = ? AND url = ?";
        jdbcTemplate.update(deleteSql, username, url);
        
        String selectSql = "SELECT url, pwd FROM manager WHERE username = ?";
        List<Map<String, Object>> passwordEntries = jdbcTemplate.queryForList(selectSql, username);
        model.addAttribute("passwordEntries", passwordEntries);
        
        return "passwordmanager";
    } catch (Exception e) {
        e.printStackTrace();
        return "error2";
    }
}

}
