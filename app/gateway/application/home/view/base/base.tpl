<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>{block name="title"}{$_seo.title}{/block}</title>
    <link rel="stylesheet" href="{$Think.config.PUBLIC_PATH}/static/libs/bootstrap/dist/css/bootstrap.min.css">
    <script src="{$Think.config.PUBLIC_PATH}/static/libs/jquery/dist/jquery.min.js"></script>
    <script src="{$Think.config.PUBLIC_PATH}/static/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="{$Think.config.PUBLIC_PATH}/static/theme/home/css/common.css">
    <script type="text/javascript" src="{$Think.config.PUBLIC_PATH}/static/libs/seajs-3.0.0/sea.js"></script>
    <script type="text/javascript" src="{$Think.config.PUBLIC_PATH}/static/libs/seajs-3.0.0/sea-plugin.js"></script>
    <script type="text/javascript" charset="UTF-8"
            src="{$Think.config.PUBLIC_PATH}/static/theme/home/sea.conf.js"></script>
    <script src="{$Think.config.PUBLIC_PATH}/static/libs/wow.js"></script>
    <link rel="stylesheet" href="{$Think.config.PUBLIC_PATH}/static/libs/animate/animate-3.5.1.css">
    <script>
        wow = new WOW(
            {
                boxClass: 'wow',      // default
                animateClass: 'animated', // default
                offset: 0,          // default
                mobile: true,       // default
                live: true        // default
            }
        )
        wow.init();
    </script>

</head>
<body>
{block name="navbar"}
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">{$_seo.title}</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="{:url('picture/index')}">来一发</a></li>
                <li><a href="{:url('account/import_account')}">标签库</a></li>
                <li><a href="{:url('account/index')}">精品套图</a></li>
                <li><a href="{:url('control/index')}">功能中心</a></li>
                <li><a href="{:url('user/index')}">个人中心</a></li>
            </ul>
        </div>
    </nav>
{/block}
<div style="padding: 10px">
    <div class="container-fluid">
        {block name="body"}{/block}
    </div>
</div>
<div class="container">
    {block name="footer"}{/block}
</div>
</body>
</html>