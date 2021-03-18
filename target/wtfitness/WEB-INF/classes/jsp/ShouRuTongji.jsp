<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style="height: 100%">
<script src="${pageContext.request.contextPath}/static/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.2.1.min.js"></script>
<head>
    <meta charset="utf-8">
    <title>收入统计图</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>


<script type="text/javascript">
       var dom = document.getElementById("container");
       var myChart = echarts.init(dom);
       var app = {};
       var option;

       option = {
           title: {
               text: '收入统计'
           },
           tooltip: {
               trigger: 'axis'
           },
           legend: {
               data: ['2020', '2021']
           },
           toolbox: {
               show: true,
               feature: {
                   dataView: {show: true, readOnly: false},
                   magicType: {show: true, type: ['line', 'bar']},
                   restore: {show: true},
                   saveAsImage: {show: true}
               }
           },
           calculable: true,
           xAxis: [
               {
                   type: 'category',
                   data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
               }
           ],
           yAxis: [
               {
                   type: 'value'
               }
           ],

           series: [
               {
                   name: '2020',
                   type: 'bar',
                   data: [],
                   markPoint: {
                       data: [
                           {type: 'max', name: '最大值'},
                           {type: 'min', name: '最小值'}
                       ]
                   },
                   markLine: {
                       data: [
                           {type: 'average', name: '平均值'}
                       ]
                   }

               },

               {
                   name: '2021',
                   type: 'bar',
                   data: [],
                   markPoint: {
                       data: [
                           {name: '年最高', value: 182.2, xAxis: 7, yAxis: 183},
                           {name: '年最低', value: 2.3, xAxis: 11, yAxis: 3}
                       ]
                   },
                   markLine: {
                       data: [
                           {type: 'average', name: '平均值'}
                       ]
                   }
               }
           ]
       };

       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
       $.post('${pageContext.request.contextPath}/cz?method=tongji2',{},function(data){
           //重新给table绑定数据
           myChart.setOption({
               xAxis: {
                   data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
               },
               series: [{
                   // 根据名字对应到相应的系列
                   name: '2021',
                   data: data.data
               }]
           });
       }) ;
       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
       $.post('${pageContext.request.contextPath}/cz?method=tongji1',{},function(data){
           //重新给table绑定数据
           myChart.setOption({
               xAxis: {
                   data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
               },
               series: [{
                   // 根据名字对应到相应的系列
                   name: '2020',
                   data: data.data
               }]
           });
       }) ;


       if (option && typeof option === 'object') {
           myChart.setOption(option);
       }

</script>
</body>
</html>
    