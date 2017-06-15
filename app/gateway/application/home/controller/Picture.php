<?php
namespace app\home\controller;

use think\Response;


/**
 * Created by IntelliJ IDEA.
 * User: zhaojunlike
 * Date: 6/4/2017
 * Time: 10:44 AM
 */
class Picture extends Base
{

    public function index()
    {
        //http
        return $this->fetch();
    }


    //获取pic
    public function getPictureJson()
    {
        $remote_id = $this->request->request("remote_id");
        if ($remote_id <= 0) {
            $this->error("哎哟,小伙子,想干啥???");
        }
        $data = \HttpHelper::http($this->build_uri($this->ResourceUris['get_picture']), [
            'p' => 1,
            'ps' => 100,
            'token' => self::$token,
            'remote_id' => $remote_id], "GET");

        //这里需要转化data
        foreach ($data['data'] as &$item) {
            $item['src'] = $item['loacal_path'];
            $item['pid'] = $item['id'];
            $item['alt'] = $item['id'];
        }
        $json['id'] = 1;
        $json['status'] = 1;
        $json['start'] = 0;
        $json['msg'] = "msg";
        $json['data'] = $data['data'];
        //json
        $data['data'] = $json;
        return Response::create($data, 'json')->code(200);
    }

    public function showList()
    {
        $remote_id = $this->request->request("remote_id");
        if ($remote_id <= 0) {
            $this->error("哎哟,小伙子,想干啥???");
        }
        $data = \HttpHelper::http($this->build_uri($this->ResourceUris['get_picture']), [
            'p' => 1,
            'ps' => 100,
            'token' => self::$token,
            'remote_id' => $remote_id], "GET");
        $this->assign('data_list', $data['data']);
        return $this->fetch("show_list");
    }

    private function getDocument($p, $ps)
    {
        $data = \HttpHelper::http($this->build_uri($this->ResourceUris['get_document']), [
            'p' => $p,
            'token' => self::$token,
            'ps' => $ps
        ], "GET");
        return $data;
    }

    public function api_get_document()
    {
        $p = $this->request->request("p", 1);
        $ps = $this->request->request("ps", 18);
        $data = $this->getDocument($p, $ps);
        return Response::create($data, 'json')->code(200);
    }

    public function add()
    {
        if ($this->request->isPost()) {

        } else {
            return $this->fetch();
        }
    }
}