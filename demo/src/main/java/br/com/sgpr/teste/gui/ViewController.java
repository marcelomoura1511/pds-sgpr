package br.com.sgpr.teste.gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping(path = "login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping(path = "admin")
    public String getAdminPage(){
        return "admin.html";
    }

    @GetMapping(path = "busca")
    public String getBuscaPage(){
        return "busca.html";
    }

    @GetMapping(path = "userHomepage")
    public String getUserHomepagePage(){
        return "userHomepage.html";
    }

    @GetMapping(path = "validate")
    public String getValidate(){
        return "validate.html";
    }
}
