{extend name="base/base" /}
{block name="navbar"}
{/block}
{block name="title"}
    {$_seo.title}
{/block}
{block name="body"}
    <div class="row" id="app">
        <div class="picture-box">
            {foreach $data_list as $item}
                <div class="col-xs-4 wow flipInY animated">
                    <img src="{$item.loacal_path}" alt="">
                </div>
            {/foreach}
        </div>
    </div>
{/block}
