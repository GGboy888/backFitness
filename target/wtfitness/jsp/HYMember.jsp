<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
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
    <script src="${pageContext.request.contextPath}/static//bootstrap/js/tableExport.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/date/bootstrap-datetimepicker.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/date/Moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/date/bootstrap-datetimepicker.min.js"></script>
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    </style>
</head>
<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/memberType?method=findAll",function (releset) {
            var e=releset.data;
            $(e).each(function () {
                $('#ktype').append('<option value='+this.typeId+' >'+this.typeName+'</option>');
            })
        });
    });
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
            ,url: '${pageContext.request.contextPath}/member?method=viewAll' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: '#headerBtns' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'memberId', title: 'ID', sort: true}
                ,{field: 'memberName', title: '用户名'}
                ,{field: 'memberPassword', title: '密码'}
                ,{field: 'memberPhone', title: '手机'}
                ,{field: 'memberSex', title: '性别',
                templet:function (d) {
                    if(d.memberSex==0){
                        return "女";
                    }else {
                        return "男";
                    }
                }}
                ,{field: 'memberAge', title: '年龄'}
                ,{field: 'birthday', title: '出生日期',
                    templet:'<div>{{ layui.util.toDateString(d.birthday, "yyyy-MM-dd ") }}</div>' }
                ,{field: 'nenberDate', title: '注册日期',
                    templet:'<div>{{ layui.util.toDateString(d.nenberDate, "yyyy-MM-dd ") }}</div>'}
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
                ,{field: 'memberStatic', title: '状态',
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
                        }else  {
                            return d.memberBalance;
                        }
                    }}
                ,{field: 'memberXufei', title: '续费',
                    templet : "<div>{{layui.util.toDateString(d.memberXufei, 'yyyy-MM-dd')}}</div>"}
                , {title: "操作",width:130, toolbar: "#opBtns"}
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
            addNew();
        });
        //批量删除
        $("#delBtn").click(function () {
            delMany();
        });

        function addNew() {
            $('#exampleModal').modal('show');
            $.post("${pageContext.request.contextPath}/memberType?method=findAll",function (releset) {
                var e=releset.data;
                var tt ="";
                var tttt="";
                var ttt="<option value='-1'>"+"--请选择--"+"</option>";
                $(e).each(function () {
                    tt += "<option value='"+this.typeId+"'>"+""+this.typeName+"</option>";
                    tttt=ttt+tt;
                    $('#optype').html(tttt);
                })


            })
        }
        //添加按钮
        $("#insert").click(function () {
           insert();
        });

        function insert() {
            if(!validateAdd()){
                return;
            }
            var name=$('#name').val();
            var password = $('#password').val();
            var phone =$('#phone').val();
            var sex=$('#sex').val();
            var srdata=$('#srdata').val();
            var optype=$('#optype').val();
            var data=$('#data').val();
            var age=$('#age').val();

            $.post("${pageContext.request.contextPath}/memberType?method=findById&id="+optype,function (releset) {
                var typetian=releset.typeDay;

                var date1 = new Date();
                $.post("${pageContext.request.contextPath}/member?method=insert&name="+name+"&password="+password+"&phone="+phone+"&sex="+sex+"&age="+age+"&srdata="+srdata+"&data="+data+"&optype="+optype+"&memberxufei="+data,function (releset) {
                    console.log(releset)


                    if(releset.data >0){
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
            })
        }



        //批量删除的具体方法
        function delMany() {
            //获取选择中的id
            var checkStatus = layui.table.checkStatus("memberId");
            var data = checkStatus.data;
            if(data.length==0){
                layer.msg("请先选择要删除的数据");
                return false;
            }
            layer.confirm("确定删除选中的会员吗？",function (index) {
                delMember(data,index);
            })
        }

        //删除用户的方法
        function delMember(data, index) {
            var ids = "";
            var i = 0;
            $.each(data,function (index, value) {
                ids = ids + "&id="+value.memberId//回后台的是&id=1&id=2&id=
                i++;
            });
           $.post("/member?method=batchDel"+ids,function (rs) {
                if(rs.data==i){
                    $('#exampleModal').modal('hide');
                    $("#searchBtn").click();
                    layer.close(index);
                    swal(
                        {
                            title:"删除成功",
                            type:"success",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }else {
                    layer.close(index);
                    swal(
                        {
                            title:"删除失败",
                            type:"warning",
                            timer: 1500,
                            showConfirmButton: false
                        }
                    )
                }

            });
        }
        //为行工具栏绑定事件
        table.on("tool(test)", function (d) {
            var event = d.event;
            var data = d.data;
            if (event == "edit") {
                //调用更新方法
                $('#updateeModal').modal('show');
                $('#upid').val(data.memberId);
                $.post("${pageContext.request.contextPath}/memberType?method=findAll",function (releset) {
                    var e=releset.data;
                    var tt ="";
                    var tttt="";
                    var ttt="<option value='-1'>"+"--请选择--"+"</option>";
                    $(e).each(function () {
                        tt += "<option value='"+this.typeId+"'>"+""+this.typeName+"</option>";
                        tttt=ttt+tt;
                        $('#upoptype').html(tttt);
                    })
                })
            }
            if (event == "delete") {

                delById(data)
            }
        });

        function delById(data) {
            layer.confirm("确定删除选中的商品吗？", function (index) {
                delId(data, index);
            });
        }

        function delId(data, index) {
            var id = data.memberId;
            $.post("/member?method=delById&id=" + id, function (rs) {
                //判断业务响应码
                if (rs.data>0) {
                    layer.msg("删除成功");
                    $("#searchBtn").click();
                    layer.close(index);
                    return false;
                }
                layer.msg("删除失败");
                layer.close(index)
            });
        }
        $('#update').click(function () {
            upd();
        });

        //编辑信息的具体方法
        function upd() {
            if(!validateUpd()){
                return;
            }
            var id=$('#upid').val();
            var name=$('#upname').val();
            var password=$('#uppaw').val();
            var phone =$('#upphone').val();
            var sex=$('#upsex').val();
            var srdata=$('#upsrdata').val();
            var optype=$('#upoptype').val();
            var data=$('#updata').val();
            var age=$('#upage').val();

            $.post("${pageContext.request.contextPath}/memberType?method=findById&id="+optype,function (releset) {
                var typetian=releset.typeDay;

                var date1 = new Date();
                $.post("${pageContext.request.contextPath}/member?method=updata&id="+id+"&name="+name+"&password="+password+"&phone="+phone+"&sex="+sex+"&age="+age+"&srdata="+srdata+"&data="+data+"&optype="+optype+"&memberxufei="+data,function (releset) {
                    if(releset.data >0){
                        $('#updateeModal').modal('hide');
                        swal(
                            {
                                title:"修改成功",
                                type:"success",
                                timer: 1500,
                                showConfirmButton: false
                            }
                        )
                    }else{
                        swal(
                            {
                                title:"修改失败",
                                type:"warning",
                                timer: 1500,
                                showConfirmButton: false
                            }
                        )
                    }
                })
            })
        }
        function validateUpd() {
            $("#upname").parent().find("span").remove();
            $("#uppaw").parent().find("span").remove();
            $("#upphone").parent().find("span").remove();
            $("#upage").parent().find("span").remove();
            $("#upsrdata").parent().find("span").remove();
            $("#upoptype").parent().find("span").remove();

            var upname = $("#upname").val().trim();
            if(upname == null || upname == ""){
                $("#upname").parent().append("<span style='color:red'>请填写姓名</span>");
                return false;
            }
            var uppaw = $("#uppaw").val().trim();
            if(uppaw == null || uppaw == ""){
                $("#uppaw").parent().append("<span style='color:red'>请填写密码</span>");
                return false;
            }

            var upphone = $("#upphone").val().trim();
            if(upphone == null || upphone == ""){
                $("#upphone").parent().append("<span style='color:red'>请填写手机号</span>");
                return false;
            }

            if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(upphone))){
                $("#upphone").parent().append("<span style='color:red'>手机号码格式不正确</span>");
                return false;
            }

            var upage = $("#upage").val().trim();
            if(upage == null || upage == ""){
                $("#upage").parent().append("<span style='color:red'>请填写年龄</span>");
                return false;
            }

            if(!(/^[1-9]\d*$/.test(upage))){
                $("#upage").parent().append("<span style='color:red'>年龄只能为正整数</span>");
                return false;
            }


            var upsrdata = $("#upsrdata").val().trim();
            if(upsrdata == null || upsrdata == ""){
                $("#upsrdata").parent().append("<span style='color:red'>请选择生日</span>");
                return false;
            }

            var upoptype = $("#upoptype").val().trim();
            if(upoptype==-1){
                $("#upoptype").parent().append("<span style='color:red'>请选择会员卡型</span>");
                return false;
            }

            return true;
        }
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

    //校验
    function validateAdd() {
        $("#name").parent().find("span").remove();
        $("#phone").parent().find("span").remove();
        $("#age").parent().find("span").remove();
        $("#srdata").parent().find("span").remove();
        $("#optype").parent().find("span").remove();

        var name = $("#name").val().trim();
        if(name == null || name == ""){
            $("#name").parent().append("<span style='color:red'>请填写姓名</span>");
            return false;
        }
        var name = $("#password").val().trim();
        if(name == null || name == ""){
            $("#name").parent().append("<span style='color:red'>请填写密码</span>");
            return false;
        }

        var phone = $("#phone").val().trim();
        if(phone == null || phone == ""){
            $("#phone").parent().append("<span style='color:red'>请填写手机号</span>");
            return false;
        }

        if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))){
            $("#phone").parent().append("<span style='color:red'>手机号码格式不正确</span>");
            return false;
        }

        var age = $("#age").val().trim();
        if(age == null || age == ""){
            $("#age").parent().append("<span style='color:red'>请填写年龄</span>");
            return false;
        }

        if(!(/^[1-9]\d*$/.test(age))){
            $("#age").parent().append("<span style='color:red'>年龄只能为正整数</span>");
            return false;
        }


        var srdata = $("#srdata").val().trim();
        if(srdata == null || srdata == ""){
            $("#srdata").parent().append("<span style='color:red'>请选择生日</span>");
            return false;
        }

        var optype = $("#optype").val().trim();
        if(optype==-1){
            $("#optype").parent().append("<span style='color:red'>请选择会员卡型</span>");
            return false;
        }
        return true;
    }

</script>
<body>
<div>

        <div class="panel-body">
            <form class="form-inline">
                <div  class="input-group input-daterange">
                    <label for="hyid" class="control-label">姓名:</label>
                    <input id="hyid" type="text" class="form-control" name="hyid">
                </div>
                <div  class="input-group input-daterange">
                    <label for="ktype" class="control-label">卡型:</label>
                    <select class="form-control" id="ktype" name="ktype">
                        <option value="0">请选择</option>
                    </select>
                </div>
                <button id="searchBtn" type="button" class="btn btn-default" style="margin-top: 20px" >查询</button>
            </form>
        </div>

    <!-- 头工具栏-->
    <script type="text/html" id="headerBtns">
        <button class="layui-btn layui-btn-sm" lay-event="addNewMember" id="addBtn" >新增用户</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delMany" id="delBtn">批量删除</button>
    </script>
    <!--行工具栏-->
    <script type="text/html" id="opBtns">
        <button class="layui-btn layui-btn-sm" lay-event="edit" >编辑</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete" >删除</button>
    </script>
    <div>
        <table class="layui-hide" id="demo" lay-filter="test"></table>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">会员添加</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="name" class="control-label">姓名:</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">密码:</label>
                        <input type="text" class="form-control" id="password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label">电话:</label>
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="sex" class="control-label">性别:</label>
                        <select class="form-control" id="sex" name="sex">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="age" class="control-label">年龄:</label>
                        <input type="text" class="form-control" id="age" name="age">
                    </div>
                    <div class="form-group">
                        <label for="srdata" class="control-label">生日:</label>
                        <input type="date" class="form-control" id="srdata" name="srdata">
                    </div>
                    <div class="form-group">
                        <label for="optype" class="control-label">卡型:</label>
                        <select class="form-control" id="optype" name="optype">

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="data" class="control-label">时间:</label>
                        <input type="date" class="form-control" id="data"  name="data">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="insert">添加</button>
            </div>
        </div>
    </div>
</div>

<%--修改--%>
<div class="modal fade" id="updateeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updateModalLabel">会员修改</h4>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" id="upid">
                    <div class="form-group">
                        <label for="name" class="control-label">姓名:</label>
                        <input type="text" class="form-control" id="upname">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">密码:</label>
                        <input type="text" class="form-control" id="uppaw">
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label">电话:</label>
                        <input type="text" class="form-control" id="upphone">
                    </div>
                    <div class="form-group">
                        <label for="sex" class="control-label">性别:</label>
                        <select class="form-control" id="upsex">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="age" class="control-label">年龄:</label>
                        <input type="text" class="form-control" id="upage">
                    </div>
                    <div class="form-group">
                        <label for="srdata" class="control-label">生日:</label>
                        <input type="date" class="form-control" id="upsrdata">
                    </div>
                    <div class="form-group">
                        <label for="optype" class="control-label">卡型:</label>
                        <select class="form-control" id="upoptype">

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="data" class="control-label">时间:</label>
                        <input type="date" class="form-control" id="updata" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="update">修改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
        