package com.example.rpcadmin.controller;

import com.example.rpcadmin.ZookeeperFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(ModelMap map) {
        Map<String, Object> resource = new HashMap<>();
        resource.put("name", "xuwei");

        map.addAttribute("resource",resource);
        return "index";
    }

    @ResponseBody
    @GetMapping("/list")
    public Object list(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        CuratorFramework client = ZookeeperFactory.create();
//        List<String> list = client.getChildren().forPath("/netty");
//        ///netty/192.168.0.102#8081#1#0000000012
//        List<Map<String, Object>> data = list.stream().map(e -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("ip", StringUtils.substringBefore(e,  "#"));
//            map.put("port", StringUtils.substringBetween(e, "#", "#"));
//            map.put("weight", StringUtils.substringAfterLast(StringUtils.substringBeforeLast(e, "#"), "#"));
//            return map;
//        }).collect(Collectors.toList());

        String port = request.getParameter("port");
        Map<String, Object> row1 = new HashMap<>();
        row1.put("ip", "192.0.0.1");
        row1.put("port", 1);
        row1.put("weight", 100);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("ip", "192.0.0.2");
        row2.put("port", 2);
        row2.put("weight", 100);

        Map<String, Object> row3 = new HashMap<>();
        row3.put("ip", "192.0.0.3");
        row3.put("port", 14);
        row3.put("weight", 100);

        List<Map<String, Object>> data = new ArrayList<>();
        data.add(row1);
        data.add(row2);
        data.add(row3);

        if (StringUtils.isNotEmpty(port)) {
            data = data.stream().filter(e->{
                int p = (int) e.get("port");
                return p == Integer.parseInt(port);
            }).collect(Collectors.toList());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", 0);
        result.put("data", data);
        return result;
    }
}
