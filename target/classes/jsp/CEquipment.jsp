
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/static/jquery-3.2.1.min.js">
    window.onunload(alert("yemian"))
</script>
<html>
<head>
    <title>器材管理</title>
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
    <script type="text/javascript">

        layui.config({
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
                ,url: '${pageContext.request.contextPath}/qc?method=viewAll' //数据接口
                ,title: '用户表'
                ,page: true //开启分页
                ,toolbar: '#headerBtns' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,totalRow: true //开启合计行
                , cols: [[ //表头
                    {field: 'eqId', title: '编号', sort: true}
                    ,{field: 'eqName', title: '器材名称'}
                    ,{field: 'eqText', title: '器材说明'}
                    ,{title: "操作", toolbar: "#opBtns"}
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

            //新增
            $("#addBtn").click(function () {
                addnew();
            });
            table.on("tool(test)", function (d) {
                var event = d.event;
                var data = d.data;
                if (event == "delete") {
                    del(data);

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
        function chaxun(){
            var opt=$('#table').bootstrapTable('getOptions');
            var eqname=$('#eqname').val();
            $.post("${pageContext.request.contextPath}/qc/query",{"pageSize":opt.pageSize,"pageNumber":opt.pageNumber,"hyname":eqname},function (data) {

                $("#table").bootstrapTable('load',data) ;
            })
        }

        function del(data) {
            var eqId = data.eqId;
            $.post("${pageContext.request.contextPath}/qc?method=delete&id="+eqId,function (releset) {
                if(releset.data>0){
                    swal(
                        {
                            title:"删除成功",
                            type:"success",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }else{
                    swal(
                        {
                            title:"删除失败",
                            type:"warning",
                            timer: 1500,
                            showConfirmButton: false

                        }
                    )
                }
            })
        }


        //添加
        function addnew() {
            $("#upname").val("");
            $("#uptext").val("");
            $('#exampleModal').modal('show');

        }



        function tianjia() {
            if(!validateAdd()){
                return;
            }
            var name=$('#upname').val();
            var text1 =$('#uptext').val();
            $.post("${pageContext.request.contextPath}/qc?method=insert&eqName="+name+"&eqText="+text1,function (releset) {
                if(releset.data>0){
                    $('#exampleModal').modal('hide');
                    swal(
                        {
                            title:"添加成功",
                            type:"success",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }else{
                    swal(
                        {
                            title:"添加失败",
                            type:"warning",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }
            })
        }

        function validateAdd() {
            $("#upname").parent().find("span").remove();
            var upname = $("#upname").val().trim();
            if(upname == null || upname == ""){
                $("#upname").parent().append("<span style='color:red'>请输入器材名称</span>");
                return false;
            }

            return true;
        }


    </script>
</head>
<body>

<%--    //查询--%>
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-inline">
            <div  class="input-group input-daterange">
                <label for="eqname" class="control-label">器材名称:</label>
                <input id="eqname" type="text" class="form-control">
            </div>
            <button onclick="chaxun()" type="button" class="btn btn-default" style="margin-top: 20px" >查询</button>
        </form>
    </div>

</div>
<!-- 头工具栏-->
<script type="text/html" id="headerBtns">
    <button class="layui-btn layui-btn-sm" lay-event="addNewMember" id="addBtn" >添加器材</button>
</script>
<!--行工具栏-->
<script type="text/html" id="opBtns">
    <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete" >删除</button>
</script>
<%--//页面数据展示--%>
<div>
    <table class="layui-hide" id="demo" lay-filter="test"></table>
</div>
<%--修改--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updateModalLabel">器材添加</h4>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" id="upid">
                    <div class="form-group">
                        <label class="control-label">器材名称:</label>
                        <input type="text" class="form-control" id="upname">
                    </div>
                    <div class="form-group">
                        <label  class="control-label">器材说明:</label>
                        <input type="text" class="form-control" id="uptext">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="tianjia()">添加</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
