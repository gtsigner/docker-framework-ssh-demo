package org.oeynet.godtoy.contollers.base;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaojunlike on 6/3/2017.
 */
@Result(type = "json")
public class JsonActionSupport {

    protected final String RET_JSON = "json";
    private Map<String, Object> jsonResult = null;

    @JSON(serialize = false)
    public Map<String, Object> getJsonResult() {
        return jsonResult;
    }

    public JsonActionSupport() {
        super();
        this.jsonResult = new HashMap<String, Object>();
        this.jsonResult.put("code", 200);
        this.jsonResult.put("msg", "Hello Api");
        this.jsonResult.put("data", "");
    }

    public void setApiCode(int apiCode) {
        this.jsonResult.put("code", apiCode);
    }

    public void setApiMsg(String apiMsg) {
        this.jsonResult.put("msg", apiMsg);
    }

    public void setApiData(Object apiData) {
        this.jsonResult.put("data", apiData);
    }

    //ServletActionContext
    //
    protected Object getParams(String paramsKey, String type) {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(type);
        return request.getParameter(paramsKey);
    }

    protected Object getParams(String paramsKey) {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        return request.getParameter(paramsKey);
    }
}
