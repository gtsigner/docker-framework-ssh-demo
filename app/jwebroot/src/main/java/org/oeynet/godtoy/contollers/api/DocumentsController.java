package org.oeynet.godtoy.contollers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 文档
 * Created by zhaojunlike on 6/1/2017.
 */
@Controller
@RequestMapping("/api/doc/")
public class DocumentsController {
    @RequestMapping(value = "/getList/{category_id}", method = RequestMethod.GET)
    public void getArticleCategory(HttpServletRequest request, HttpServletResponse response, @PathVariable String category) {
        System.out.println("Hello");
        StringBuilder msg = new StringBuilder();
        // TODO ②
        msg.append("{\"msg\":\"").append(category).append("——这是你刚才传入的第二个参数\"}");
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
            out.println(msg);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
