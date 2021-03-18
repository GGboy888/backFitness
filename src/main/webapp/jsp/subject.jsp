
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/static/jquery-3.2.1.min.js">
    window.onunload(alert("jin7"))
</script>
<html>
<head>
    <title>课程列表</title>
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

    <script>
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
                ,url: '${pageContext.request.contextPath}/subject?method=viewAll' //数据接口
                ,title: '用户表'
                ,page: true //开启分页
                ,toolbar: '#headerBtns' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                ,totalRow: true //开启合计行
                , cols: [[ //表头
                    {field: 'subId', title: '课程编号',  sort: true}
                    ,{field:'subName', title: '课程名称'}
                    ,{field:'subPrice', title: '课程售价'}
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
                $("#myModal").modal("show");
            });

            //为行工具栏绑定事件
            table.on("tool(test)", function (d) {
                var event = d.event;
                var data = d.data;
                if (event == "edit") {
                    //调用更新方法
                    $('#myModal2').modal('show');
                    $('#id').val(data.subId);
                }
                if (event == "delete") {
                    del1(data)

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
        //删除
        function del1(data){
            var id = data.subId;

            swal({
                    title: "确定删除吗？",
                    text: "您将无法恢复！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    cancelButtonText: "取消删除！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.post('${pageContext.request.contextPath}/subject?method=del&id='+id,function(data){
                            if(data.data>0){
                                swal("删除！", "删除成功",
                                    "success");
                            }else {
                                swal("失败！", "删除失败)",
                                    "error");
                            }

                        }) ;
                    } else {
                        swal("取消！", "您已取消删除)",
                            "error");
                    }
                });
        }

        //获取当前的条件个页面页数即使更新值
        function queryParams(afds){
            var i={
                "pageSize":afds.pageSize,
                "pageNumber":afds.pageNumber,
                "id":$('#cardid').val(),
            };
            return i;
        }
        //查询
        function search(){
            var opt=$('#table').bootstrapTable('getOptions');
            var subjectid=$('#cardid').val();

            $.post("${pageContext.request.contextPath}/subject/query",{"pageSize":opt.pageSize,"pageNumber":opt.pageNumber,"subname":subjectid},function (releset) {
                $("#table").bootstrapTable('load',releset) ;
            })
        }

        function save(){
            if(!validateAdd()){
                return;
            }
            var name=$("#name").val();
            var money = $("#money").val();
            $("#myModal").modal("hide") ;
            $.post("${pageContext.request.contextPath}/subject?method=count&subname="+name,function (releset) {
                if(releset.data<1){
                    $.post('${pageContext.request.contextPath}/subject?method=add&subname='+name+"&sellingPrice="+money,function(data){

                        if(data.data>0){
                            swal("添加！", "添加成功",
                                "success");
                        }else {
                            swal("失败！", "添加失败",
                                "error");
                        }

                    }) ;
                }else if(releset.data>0){
                    swal("失败！", "已有该课程，请重新输入！",
                        "error");
                }
            })
        }

        function validateAdd() {
            $("#name").parent().find("span").remove();
            $("#money").parent().find("span").remove();


            var name = $("#name").val().trim();
            if(name == null || name == ""){
                $("#name").parent().append("<span style='color:red'>请输入课程名称</span>");
                return false;
            }

            var money = $("#money").val().trim();
            if(money == null || money == ""){
                $("#money").parent().append("<span style='color:red'>请输入费用</span>");
                return false;
            }


            if(!(/^[0-9,.]*$/.test(money))){
                $("#money").parent().append("<span style='color:red'>费用只能为正整数或小数</span>");
                return false;
            }

            return true;
        }
        function upd(){
            if(!validateUpd()){
                return;
            }
            var id =  $('#id').val();
            var name = $("#xgname").val();
            var money= $("#xgmoney").val();
            $("#myModal2").modal("hide") ;
            $.post("${pageContext.request.contextPath}/subject?method=count&subname="+name,function (releset) {
                if(releset.data<1){
                $.post('${pageContext.request.contextPath}/subject?method=upd&subId='+id+"&subname="+name+"&sellingPrice"+money,function(data){
                if(data.data>0){
                    swal("修改！", "修改成功",
                        "success");
                }else {
                    swal("失败！", "修改失败",
                        "error");
                }

               });
                }else if(releset.data>0){
                    swal("失败！", "已有该课程，请重新输入！",
                        "error");
                }
            })
        }

        function validateUpd() {
            $("#xgname").parent().find("span").remove();
            $("#xgmoney").parent().find("span").remove();


            var xgname = $("#xgname").val().trim();
            if(xgname == null || xgname == ""){
                $("#xgname").parent().append("<span style='color:red'>请输入课程名称</span>");
                return false;
            }

            var xgmoney = $("#xgmoney").val().trim();
            if(xgmoney == null || xgmoney == ""){
                $("#xgmoney").parent().append("<span style='color:red'>请输入费用</span>");
                return false;
            }


            if(!(/^[0-9,.]*$/.test(xgmoney))){
                $("#xgmoney").parent().append("<span style='color:red'>费用只能为正整数或小数</span>");
                return false;
            }

            return true;
        }


    </script>
</head>
<body>
    <%--    //查询--%>

    <div class="panel-body">
        <form class="form-inline">
            <div  class="input-group input-daterange">
                <label for="cardid" class="control-label">课程名称:</label>
                <input id="cardid" type="text" class="form-control">
            </div>
            <button onclick="search()" type="button" class="btn btn-default" style="margin-top: 20px" >查询</button>

        </form>
    </div>

    <!-- 头工具栏-->
    <script type="text/html" id="headerBtns">
        <button class="layui-btn layui-btn-sm" lay-event="addNewMember" id="addBtn" >添加课程</button>
    </script>
    <!--行工具栏-->
    <script type="text/html" id="opBtns">
        <button class="layui-btn layui-btn-sm" lay-event="edit" >编辑</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete" >删除</button>
    </script>
    <%--//页面数据展示--%>
    <div>
        <table class="layui-hide" id="demo" lay-filter="test"></table>
    </div>
    <div class="modal fade" id="myModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">❤增加新课程</h4>
                </div>
                <div class="modal-body">
                    <!-- form开始 -->
                    <form>
                        <div class="form-group">
                            <label for="name" class="col-sm-4 control-label"style="margin-top: 10px">课程名称</label>
                            <div class="col-sm-8">
                                <input type="text"style="margin-top: 10px" class="form-control" id="name" parsley-trigger="change" parsley-required="true" parsley-minlength="4" parsley-type="email" parsley-validation-minlength="1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="money" class="col-sm-4 control-label"style="margin-top: 10px">费用</label>
                            <div class="col-sm-8">
                                <input type="text"style="margin-top: 10px" class="form-control" id="money" parsley-trigger="change" parsley-required="true" parsley-minlength="4" parsley-type="email" parsley-validation-minlength="1">
                            </div>
                        </div>
                    </form>
                    &nbsp;
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button onclick="save()" id = "add" type="button" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel2">❤修改课程</h4>
                </div>
                <div class="modal-body">
                    <!-- form开始 -->
                    <form>
                        <input type="hidden" id="id" name="id">
                        <div class="form-group">
                            <label for="xgname" class="col-sm-4 control-label"style="margin-top: 10px">课程名称</label>
                            <div class="col-sm-8">
                                <input type="text"style="margin-top: 10px" class="form-control" id="xgname" parsley-trigger="change" parsley-required="true" parsley-minlength="4" parsley-type="email" parsley-validation-minlength="1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="xgmoney" class="col-sm-4 control-label"style="margin-top: 10px">课程费用</label>
                            <div class="col-sm-8">
                                <input type="text"style="margin-top: 10px" class="form-control" id="xgmoney" parsley-trigger="change" parsley-required="true" parsley-minlength="4" parsley-type="email" parsley-validation-minlength="1">
                            </div>
                        </div>
                    </form>
                    &nbsp;
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button onclick="upd()"  type="button" class="btn btn-primary">修改</button>
                    </div>
                </div>
            </div>
        </div></div>
</body>
</html>
