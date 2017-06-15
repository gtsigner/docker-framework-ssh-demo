package org.oeynet.godtoy.contollers;


import com.opensymphony.xwork2.ModelDriven;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;
import org.oeynet.godtoy.entites.DocumentEntity;
import org.oeynet.godtoy.services.interf.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DocumentController extends JsonActionSupport implements ModelDriven<Object> {

    @Autowired
    private DocumentService documentService;


    //获取套图api数据
    /**
     * @return
     * @params p 页
     * @params ps pageSize
     */
    public String index() {
        int p = getParams("p") != null ? Integer.parseInt((String) getParams("p")) : 1;
        int ps = getParams("ps") != null ? Integer.parseInt((String) getParams("ps")) : 20;
        System.out.println("获取数据,页面P:" + p);
        List<DocumentEntity> list = documentService.getDocumentEntityList(new DocumentEntity(), ps, p);
        setApiCode(200);
        if (list.size() <= 0) {
            setApiCode(400);
            setApiMsg("对不起,已经没有数据了");
        }
        setApiData(list);
        return RET_JSON;
    }

    //访问++
    public String view_count() {
        String uuid = (String) getParams("uuid");
        documentService.incView(uuid);
        setApiCode(200);
        setApiMsg("view+1");
        return RET_JSON;
    }

    //点赞++
    public String heart_count() {
        String uuid = (String) getParams("uuid");
        documentService.incHeart(uuid);
        setApiCode(200);
        setApiMsg("点赞成功");
        return RET_JSON;
    }

    //最新的采集套图
    public String last() {

        return RET_JSON;
    }

    //验证是否套图已经被采集
    public String check() {

        return RET_JSON;
    }

    //添加套图
    public String add() {
        DocumentEntity entity = new DocumentEntity();
//        title: "",
//                url: "",
//                remote_path: '/',
//                content: "",
//                page_num: "",
//                category_id: "",
//                create_time: "",
//                update_time: "",
//                good_count: 0,
//                view_count: 0,
        entity.setTitle((String) getParams("title"));
        entity.setContent((String) getParams("content"));
        entity.setView_count(Integer.parseInt((String) getParams("view_count")));
        entity.setRemote_id(Integer.parseInt((String) getParams("remote_id")));
        entity.setCategory_id(Integer.parseInt((String) getParams("category_id")));
        //addId
        documentService.add(entity);
        this.setApiData(entity);
        return RET_JSON;
    }

    //删除套图
    public String del() {
        return RET_JSON;
    }

    //更新套图数据
    public String update() {
        return RET_JSON;
    }


    @Override
    public Object getModel() {
        return null;
    }
}
