package org.oeynet.godtoy.contollers;

import com.opensymphony.xwork2.ModelDriven;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;
import org.oeynet.godtoy.services.interf.DocumentService;
import org.oeynet.godtoy.services.interf.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhaojunlike on 6/15/2017.
 */
public class SearchController extends JsonActionSupport implements ModelDriven<Object> {


    @Autowired
    private SearchService service;

    public String search(){
        List list=service.search();
        setApiData(list);
        return RET_JSON;
    }




    @Override
    public Object getModel() {
        return null;
    }
}
