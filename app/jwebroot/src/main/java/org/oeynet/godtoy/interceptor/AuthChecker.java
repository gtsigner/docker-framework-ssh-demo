package org.oeynet.godtoy.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.json.JSONResult;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * 权限拦截器
 */
@InterceptorRef(value = "json")
public class AuthChecker implements Interceptor {
    private final String myApiToken = "4f39500149264de474aa8fa4c67379d1".trim().toUpperCase();

    @Override
    public void destroy() {
        System.out.println("------AuthChecker.destroy------");
    }

    @Override
    public void init() {
        System.out.println("------AuthChecker.init------");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("------AuthChecker.intercept------");
        HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        Map<String, Object> auth = actionInvocation.getInvocationContext().getParameters();
        if (auth.get("token") != null && request.getParameter("token").toUpperCase().trim().equals(this.myApiToken)) {
            //&& auth.get("token").toString().equals(myApiToken)
            return actionInvocation.invoke();
        } else {
            actionInvocation.addPreResultListener(new PreResultListener() {
                @Override
                public void beforeResult(ActionInvocation actionInvocation, String s) {
                    //DocumentController
                    JsonActionSupport ac = (JsonActionSupport) actionInvocation.getAction();
                    ac.setApiCode(404);
                    ac.setApiMsg("权限认证失败!!!");
                }
            });
            return "json";
        }
    }

}
