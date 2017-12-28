//查询分页
function _query(){
    var data = query_data()
    $('#table').bootstrapTable('refresh', {
        'pageNumber':1,
        query:data
     });
}
//清除搜索报告标题
function _cleanTitle(){
    $("#title").val("");
}

//确定
function sure(){
    _query();
}

//查询参数
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
//           var searchTitle  = $("#title").val();
//           if(searchTitle){
//           $(".index-nav-confirm").css("background","#66b8f5")
//           }


           return querydata
}

//确定按钮
$(function(){
   $(".index-nav-title").mouseleave(function(){
        var searchTitle  = $("#title").val();
        if(searchTitle){
                $(".index-nav-confirm").css("background","#66b8f5")
           }
   })
})

//分页参数
function queryParams(params) {  //配置参数
    var data = query_data()
      data["pageSize"]=params.pageSize,   //页面大小
      data["pageNo"]=params.pageNumber,  //页码
      data["orderBy"]='publishDate' //排序列名
      data["order"]='desc'//排位命令（desc，asc）
    return data;
  }

  //bootstrap分页
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

//报告状态
function state(value,row,index){
    var state=row.state;
    var id = row.id;
    var useId = id+"use";
    if(state==0){
      state="<span class='on-use ' id="+useId+" data-id="+id+">使用中</span>&nbsp;<span class='on-over' id="+id+" onclick='over(this)' data-id="+id+">下架</span>"
    }
    if(state==1){
        state="<span class='on-use' id="+useId+" data-id="+id+">已下架</span>&nbsp;<span class='on-over' id="+id+" onclick='use(this)'  data-id="+id+">启用</span>"
    }
     return state;
}

//下架
function over(id){
	var spanTxt = $(id).html();
	var id = $(id).attr('data-id')
	var useId = id+"use";
	var useTxt = $("#"+useId).html();
	var staTxt= $("#sta"+id).html();
	$(".index-title").html("您确定"+spanTxt+"此报告？");
	$('.index-tips').show();

    //弹窗确定，取消操作
    $('.index-cancel').click(function(){
    	$(this).parents('.index-tips').hide();
    });
    $('.index-confirm').click(function(){
    	$(this).parents('.index-tips').hide();
    	 $.post("updateState/"+id,{"id":id},function(data){});
            spanTxt = '启用'
            useTxt = '已下架'
            staTxt='修改'
           $('#'+id).text(spanTxt);
           $('#'+useId).text(useTxt);
           $("#sta"+id).text(staTxt);
           $("#"+id).attr("onclick","use(this)");
    });
}

//启用
function use(id){
	var spanTxt = $(id).html();
	var id = $(id).attr('data-id')
	var useId = id+"use";
	var useTxt = $("#"+useId).html();
	var staTxt= $("#sta"+id).html();
	$(".index-title").html("您确定"+spanTxt+"此报告？");
	$('.index-tips').show();

    //弹窗确定，取消操作
    $('.index-cancel').click(function(){
    	$(this).parents('.index-tips').hide();
    });
    $('.index-confirm').click(function(){
    	$(this).parents('.index-tips').hide();
    	 $.post("updateStateTo/"+id,{"id":id},function(data){});
            spanTxt = '下架';
            useTxt = '使用中';
            staTxt = '详情';
           $('#'+id).text(spanTxt);
           $('#'+useId).text(useTxt);
           $("#sta"+id).text(staTxt);
           $("#"+id).attr("onclick","over(this)");
    });
}

//报告标题
function title(value,row,index){
    var title=row.title;
    var listPic = row. listPic;
    if(!listPic){
       listPic =  './img/table-pic.png'
    }
    if(title){
        if(title.length>25){
            title = title.substring(0,25)+"...";
        }
        title="<img src='"+listPic+"'/><span>"+title+"</span>"
    }
    return title;
}

//作者名字
function authorName(value,row,index){
    var authorName = row.authorName
    if(authorName){
        if(authorName.length>4){
            authorName = authorName.substring(0,4)+"...";
        }
        return authorName;
    }
}

//来源
function source(value,row,index){
    var source = row.source;
    if(source){
        if(source.length>5){
            source = source.substring(0,5)+"..."
        }
        return source;
    }
}

//修改
function update(value,row,index){
    var id = row.id;
    var state = row.state;
    if(state==1){
        var update ='<a href="update?id='+id+'"><p id="sta'+id+'">修改</p></a>';
    }
    if(state==0){
        var update = '<a href="http://ctdnqa.gi.com//report_detailed.html?id='+id+'"><p id="sta'+id+'">详情</p></a>'
    }
    return update;
}