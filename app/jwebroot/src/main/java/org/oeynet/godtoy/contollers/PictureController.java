package org.oeynet.godtoy.contollers;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.oeynet.godtoy.contollers.base.JsonActionSupport;
import org.oeynet.godtoy.entites.PictureEntity;
import org.oeynet.godtoy.services.interf.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * Created by zhaojunlike on 6/4/2017.
 */
@Controller
public class PictureController extends JsonActionSupport {

    @Autowired
    private PictureService pictureService;

    //增加图片
    public String add() {
        // { category_id: 143,
        //     img_url: 'http://www.mzitu.com/522/6',
        //     remote_path: '/522/6',
        //     url_img: 'http://i.meizitu.net/2013/06/2013061116g1fspol2lrr.jpg',
        //     path: '/143/',
        //     location: '/images/143/2013/06/2013061116g1fspol2lrr.jpg' }

        //添加套图
        PictureEntity entity = new PictureEntity();
        entity.setDocument_id(Integer.parseInt((String) getParams("category_id")));
        entity.setRemote_id(Integer.parseInt((String) getParams("remote_id")));
        entity.setRemote_url((String) getParams("url_img"));
        entity.setLoacal_path((String) getParams("location"));
        entity.setCreate_time(0);
        //entity.setLoacal_path(getParams("path"));
        pictureService.insert(entity);
        this.setApiMsg("添加成功");
        return RET_JSON;
    }


    public String del() {

        return RET_JSON;
    }

    //获取列表信息
    public String list() {
        int remote_id = Integer.parseInt((String) getParams("remote_id"));
        int p = Integer.parseInt((String) getParams("p"));
        int ps = Integer.parseInt((String) getParams("ps"));
        //get
        List<PictureEntity> list = pictureService.getList(p, ps, remote_id);
        setApiMsg("获取" + p + "页图库数据成功");
        setApiData(list);
        return RET_JSON;
    }

    public String get_list() {
        String keywords = (String) this.getParams("keywords");
        //get
        this.setApiData(pictureService.getList(keywords));
        return RET_JSON;
    }

    public void update() {

    }
}
