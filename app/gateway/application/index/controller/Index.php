<?php

namespace app\index\controller;

class Index extends Base
{
    public function index()
    {
        var_dump($_REQUEST);
        exit();
        return $this->fetch();
    }
}
