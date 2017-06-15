{extend name="base/base" /}
{block name="title"}
    {$_seo.title}
{/block}
{block name="body"}
    <div class="row" id="app" v-scroll="loadMore">
        <div class="picture-box">
            <div class="col-md-3 col-lg-2 col-sm-3 col-xs-6 picture-list-item wow flipInY animated"
                 v-for="item in data_list">
                <div class="img-box">
                    <img class="show-img" :src="item.content" alt="" @click="showPhoto(item.remote_id)">
                </div>
                <div class="content">
                    <h4 class="text-center text-primary title" v-html="item.title"></h4>
                    <div class="heart-box row">
                        <div class="col-xs-6 text-center">
                            <div class="likeCount" :data-uuid="item.uuid" v-html="item.view_count"></div>
                        </div>
                        <div class="col-xs-6 text-center">
                            <div class="heart" :data-toggle="item.uuid" rel="like" @click=""></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script>

        seajs.use(['vue', 'layer', 'layerCss', 'api'], function () {
            var API = seajs.require("api");
            $('body').on("click", '.heart', function () {
                var togUUID = $(this).attr("data-toggle");
                var target = $(".likeCount[data-uuid='" + togUUID + "']");
                var C = parseInt(target.html());
                $(this).css("background-position", "")
                var D = $(this).attr("rel");
                if (D === 'like') {
                    target.html(C + 1);
                    $(this).addClass("heartAnimation").attr("rel", "unlike");
                }
                else {
                    target.html(C - 1);
                    $(this).removeClass("heartAnimation").attr("rel", "like");
                    $(this).css("background-position", "left");
                }
            });

            var vm = new Vue({
                el: "#app",
                data: {
                    current_p: 1,
                    data_list: [],
                    isLoading: false,
                    loadingIndex: 0,
                },
                directives: {
                    scroll: {
                        bind: function (el, binding) {
                            window.addEventListener('scroll', function () {
                                if (document.body.scrollTop + window.innerHeight >= el.clientHeight) {
                                    var fnc = binding.value;
                                    fnc();
                                }
                            })
                        }
                    }
                },
                methods: {
                    /*showPhoto: function (remote_id) {
                     //相册层
                     $.post("{:url('home/picture/getPictureJson')}",{remote_id:remote_id}, function (json) {
                     if (json.code !== 200) {
                     layer.alert("小伙子,乱搞啥子？？？安，把你打成屎");
                     }
                     var fullIndex = layer.photos({
                     photos: json.data,
                     anim: 0,
                     closeBtn: true,

                     }, 'JSON');
                     });
                     },*/
                    showPhoto: function (remote_id) {
                        var index = layer.open({
                            type: 2,
                            content: "{:url('home/picture/showList')}?remote_id=" + remote_id,
                            area: ['320px', '195px'],
                            maxmin: true
                        });
                        layer.full(index)
                    },
                    loadMore(p){
                        var _self = this;
                        if (_self.isLoading === true) {
                            return false;
                        }
                        _self.isLoading = true;
                        $.get("{:url('home/picture/api_get_document')}",{p:_self.current_p}, function (ret) {
                            if (200 === ret.code) {
                                ret.data.forEach(function (item) {
                                    _self.data_list.push(item);
                                });
                                if (p) {
                                    _self.current_p = p;
                                } else {
                                    _self.current_p += 1;
                                }
                            }
                            _self.isLoading = false;
                        }, 'JSON');


                    },
                    getDocuments: function () {
                        this.loadMore(2);//加载第一页
                    }
                },
                mounted: function () {
                    this.getDocuments();
                }
            });
        });
    </script>
{/block}
