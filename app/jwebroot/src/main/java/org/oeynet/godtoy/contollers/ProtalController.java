package org.oeynet.godtoy.contollers;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by zhaojunlike on 6/1/2017.
 */

public class ProtalController extends JsonActionSupport {

    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass());

    public String login() {

        return "success";
    }
}
