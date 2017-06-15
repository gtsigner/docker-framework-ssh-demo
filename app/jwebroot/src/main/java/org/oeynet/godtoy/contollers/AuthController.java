package org.oeynet.godtoy.contollers;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by zhaojunlike on 6/3/2017.
 */
public class AuthController extends JsonActionSupport
        implements ModelDriven<Object> {

    public AuthController() {
        System.out.println("生命周期父类");

    }

    public String login() {


        return RET_JSON;
    }


    public String auth_fail() {
        setApiMsg("对不起未授权的访问");
        setApiData("");
        setApiCode(500);
        return RET_JSON;
    }

    @Override
    public Object getModel() {
        return null;
    }
}
