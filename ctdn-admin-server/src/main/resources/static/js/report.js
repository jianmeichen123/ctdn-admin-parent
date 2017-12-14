
function _query(){

    var data = query_data()
    $('#table').bootstrapTable('refresh', {
        'pageNumber':1,
        query:data
     });
}
function _cleanTitle(){
    $("#title").val("");
}

function query_data(){
    var querydata={};
       var startDate=$("#publish_time_start").val();
           var endDate=$("#publish_time_end").val();
           var sdate = [];
           var edate = [];
           var d1 = "";
           var d2 = "";
           if(startDate != '' && endDate != '') {
               sdate = startDate.split('-');
               d1 = sdate[0]+sdate[1]+sdate[2];
               edate = endDate.split('-');
               d2 = edate[0]+edate[1]+edate[2];
               if(parseInt(d1) > parseInt(d2)) {
                   alert("日期开始时间大于结束时间");
                   return false;
               }else{
                   d1 = sdate[0]+'-'+sdate[1]+'-'+sdate[2];
                   d2 = edate[0]+'-'+edate[1]+'-'+edate[2];
                   querydata["startDate"] = d1;
                   querydata["endDate"] = d2;
               }
           }else{
               querydata["startDate"] = startDate;
               querydata["endDate"] = endDate;
           }
//           querydata[$("#title").attr("data-field")] = $("#title").val();
           querydata["keyword"] = $("#title").val();
           return querydata
}
function queryParams(params) {  //配置参数
    var data = query_data()
      data["pageSize"]=params.pageSize,   //页面大小
      data["pageNo"]=params.pageNumber,  //页码
      data["orderBy"]='publishDate' //排序列名
      data["order"]='desc'//排位命令（desc，asc）
    return data;
  }
$('#table').bootstrapTable({
    url: "reports",         //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
    queryParamsType: 'size|page', // undefined
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sortable: true,                     //是否启用排序
    sortOrder: "desc",                   //排序方式
    tableDataName:'page',
    queryParams: queryParams, //参数
    undefinedText:"--",
    tableDataListName:'records',
    tableDataTotalName:'total',
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10,15,20],        //可供选择的每页的行数（*）
    formatLoadingMessage: function () {
        return "请稍等，正在加载中...";
    },
    formatNoMatches: function () {  //没有匹配的结果
        return '抱歉，没有相关的结果';
    },
    onLoadSuccess: function (data) {
            $(".index-result em").text(data.page.total)
        }
});

function state(value,row,index){
    var state=row.state;
    var id = row.id;
    if(state==0){
      state="<span class='on-use '>使用中</span>&nbsp;<span class='on-over' onclick='over("+id+")'>下架</span>"
    }
    if(state==1){
        state="<span class='on-over'>使用中</span>&nbsp;<span class='on-use'>下架</span>"
    }
     return state;
}

function over(id){

    $.post("updateState/"+id,{"id":id},function(data){

//        _query();
    })
}