package com.ruoyi.project.market.indent.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market/indent")
public class IndentController extends BaseController {

    private String prefix = "market/indent";

    @RequiresPermissions("market:indent :view")
    @GetMapping()
    public String operlog() {
        return prefix + "/indent";
    }


}
