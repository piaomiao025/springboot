package springboot.com.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangzihao on 2016/6/8.
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {


    /**
     * 通过springboot进行启动, Thymeleaf 进行相应的解析
     *
     * @param name
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{name}")
    public String getUser(@PathVariable("name") String name, ModelMap modelMap) {

        modelMap.put("name", name);

        return "test";
    }



}
