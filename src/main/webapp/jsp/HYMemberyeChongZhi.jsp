
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/static/jquery-3.2.1.min.js">
    window.onunload(alert("jin"))
</script>
<html>
<head>
    <title>会员列表</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script src="/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/table/bootstrap-table.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/table/bootstrap-table.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/table/locale/bootstrap-table-zh-CN.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/sweetalert/sweetalert.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/sweetalert/sweetalert.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/date/bootstrap-datetimepicker.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/date/Moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/date/bootstrap-datetimepicker.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <script>
        $(function () {
            $.post("${pageContext.request.contextPath}/ktype/query",{},function (releset) {
                var e=releset;
                $(e).each(function () {
                    $('#ktype').append('<option value='+this.typeId+' >'+this.typeName+'</option>');
                })
            });
        }); layui.config({
            version: '1615206508080' //为了更新 js 缓存，可忽略
        });

        layui.use([ 'laypage', 'jquery','layer', 'table',  'upload', 'element'], function(){
            var laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,$ = layui.jquery //表格
                ,upload = layui.upload //上传
                ,element = layui.element //元素操作
                ,form = layui.form;


            //监听Tab切换
            element.on('tab(demo)', function(data){
                layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                    tips: 1
                });
            });

            //执行一个 table 实例
            table.render({
                id :"memberId"
                ,elem: '#demo'
                ,cellMinWidth:80
                ,url: '${pageContext.request.contextPath}/member?method=viewAll' //数据接口
                ,title: '用户表'
                ,page: true //开启分页
                ,toolbar: '#headerBtns' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,totalRow: true //开启合计行
                , cols: [[ //表头
                    {field: 'memberId', title: '会员编号', sort: true}
                    ,{field: 'memberName', title: '名称'}
                    ,{field: 'memberPhone', title: '电话'}
                    ,{field: 'memberTypes', title: '卡类型',
                        templet:function (d) {
                            if(d.memberTypes==1){
                                return "周卡";
                            }else if(d.memberTypes==2){
                                return "月卡";
                            }else if(d.memberTypes==2){
                                return "季卡";
                            }else {
                                return "年卡";
                            }
                        }}
                    ,{field: 'nenberDate', title: '录入日期',
                        templet : "<div>{{layui.util.toDateString(d.nenberDate, 'yyyy-MM-dd')}}</div>"}
                    ,{field: 'memberStatic', title: '会员状态',
                        templet:function (d) {
                            if(d.memberStatic==1){
                                return "正常";
                            }else {
                                return "<p style='color:red'>请续卡</p>";
                            }
                        }}
                    ,{field: 'memberBalance', title: '会员余额',
                        templet:function (d) {
                            if(d.memberBalance<=0){
                                return "<p style='color:red'>请缴费</p>";
                            }else {
                                return d.memberBalance;
                            }
                        }}
                       ,{ field:'memberxufei',title:'到期日期',
                        templet : "<div>{{layui.util.toDateString(d.memberxufei, 'yyyy-MM-dd')}}</div>"}
                    , {title: "操作", toolbar: "#opBtns"}
                ]]

                ,parseData: function (rs) {//数据格式解
                    console.log(rs)
                    return {
                        "code": rs.code,
                        "msg": rs.msg,
                        "count": rs.data.counts,
                        "data": rs.data.data
                    }
                },
                response: {//设置响应码
                    statusCode: 200
                }
            });
            //为搜索按钮绑定事件
            $("#searchBtn").click(function () {

                //获取搜索框中的值
                var name = $("#hyid").val();
                var ktype = $("#ktype").val();

            });

            //为行工具栏绑定事件
            table.on("tool(test)", function (d) {
                var event = d.event;
                var data = d.data;
                if (event == "edit") {
                    //调用更新方法
                    $('#updateeModal').modal('show');
                    $('#ycy').val(data.memberId);
                }
            });


            //分页
            laypage.render({
                elem: 'pageDemo' //分页容器的id
                ,count: 100 //总页数
                ,skin: '#1E9FFF' //自定义选中色值
                //,skip: true //开启跳页
                ,jump: function(obj, first){
                    if(!first){
                        layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                    }
                }
            });
        });

        //查询
        function search(){
            var opt=$('#dg').bootstrapTable('getOptions');
            var hyid=$('#hyid').val();
            var ktype=$('#ktype').val();
            $.post("${pageContext.request.contextPath}/menber/query",{"pageSize":opt.pageSize,"pageNumber":opt.pageNumber,"hyname":hyid,"ktype":ktype},function (releset) {
                $("#dg").bootstrapTable('load',releset) ;
            })
        }

        function cz() {
            if(!validateAdd()){
                return;
            }
            var id=$('#ycy').val();
            var jine=$('#xzmoney').val();
            var ssjine=$('#ssmoney').val();
            var zhaoqian=$('#zhaoqian').val();
            var bz=$('#bz').val();
            $.post("${pageContext.request.contextPath}/cz?method=xin&id="+id+"&jine="+jine+"&ssjine="+ssjine+"&zhaoqian="+zhaoqian+"&bz"+bz,function (releset) {
                if(releset.data>0){
                    $('#updateeModal').modal('hide');
                    swal(
                        {
                            title:"续费成功",
                            type:"success",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }else{
                    swal(
                        {
                            title:"续费失败",
                            type:"warning",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }
            })
        }

        function validateAdd() {
            $("#xzmoney").parent().find("span").remove();
            $("#ssmoney").parent().find("span").remove();


            var xzmoney = $("#xzmoney").val().trim();
            if(xzmoney == null || xzmoney == ""){
                $("#xzmoney").parent().append("<span style='color:red'>请填写金额</span>");
                return false;
            }

            if(!(/^[1-9]\d*$/.test(xzmoney))){
                $("#xzmoney").parent().append("<span style='color:red'>金额只能为正整数</span>");
                return false;
            }

            var ssmoney = $("#ssmoney").val().trim();
            if(ssmoney == null || ssmoney == ""){
                $("#ssmoney").parent().append("<span style='color:red'>请填写实收金额</span>");
                return false;
            }

            if(!(/^[1-9]\d*$/.test(ssmoney))){
                $("#ssmoney").parent().append("<span style='color:red'>实收金额只能为正整数</span>");
                return false;
            }



            return true;
        }

        function zql() {
            var jine=$('#xzmoney').val();
            var ssjine=$('#ssmoney').val();
            var zhao=ssjine-jine;
            $('#zhaoqian').val(zhao);
        }
    </script>
</head>
<body>
<%--    //查询--%>
    <div class="panel-body">
        <form class="form-inline">
            <div  class="input-group input-daterange">
                <label for="hyid" class="control-label">姓名:</label>
                <input id="hyid" type="text" class="form-control">
            </div>
            <div  class="input-group input-daterange">
                <label for="ktype" class="control-label">卡型:</label>
                <select class="form-control" id="ktype">
                    <option value="0">请选择</option>
                </select>
            </div>
            <button onclick="search()" type="button" class="btn btn-default" style="margin-top: 20px" >查询</button>
        </form>
    </div>
<%--//页面数据展示--%>
<!--行工具栏-->
<script type="text/html" id="opBtns">
    <button class="layui-btn layui-btn-sm" lay-event="edit" >续费</button>
</script>
<div>
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>
<%--修改--%>
<div class="modal fade" id="updateeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updateModalLabel">充值金额</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div>
                        <label for="xzmoney" class="control-label">金额:</label>
                        <input class="form-control" type="text" id="xzmoney">
                    </div>
                    <div>
                        <label for="ssmoney" class="control-label">实收金额:</label>
                        <input class="form-control" type="text" id="ssmoney" oninput="zql()">
                    </div>
                    <div>
                        <label for="zhaoqian" class="control-label">找钱:</label>
                        <input class="form-control" type="text" id="zhaoqian" readonly="readonly">
                    </div>
                    <div>
                        <label for="bz" class="control-label">备注:</label>
                        <input class="form-control" type="text" id="bz">
                    </div>
                    <input type="hidden" id="ycy">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="cz()">充值</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
