$.ajaxSetup( {
	cache: false
} );
$(document).ready(function(){
	// 加header
	$.ajax({
		url: 'header',
		type: 'GET',
		dataType: 'html',
		cache: false
	})
	.done(function(data) {
		console.log("success");
		$("#header").append(data);
		if(activeMenu)
		{
			activeMenu();
		}
	});

    $(window).scroll(function (){
        var offsetTop = $(window).scrollTop() + 50 +"px";
        $("#layout").animate({top : offsetTop },{ duration:1, queue:false });
    }); 
    $.scrollUp({
        animation: 'fade',
        activeOverlay: '#00FFFF',
        scrollImg: {
            active: true
        }
    });
    
    jQuery.validator.addMethod("selectedV", function(value, element) {
	  return this.optional(element) || value !="-1"; 
	}, $.validator.format("请选择正确的值"));
    
    jQuery.validator.addMethod("compareDate", function(value, element ,param) {
    	var target = $( param );
    	return value >= target.val();
	}, $.validator.format("结束日期必须不小于开始日期"));

	jQuery.validator.addMethod("uniqueValue", function(value, element ,param) {
		var arr = [];
		var others = $(element).closest('form').find(param).not($(element));
		$.each(others,function () {
			var va = $(this).val();
			if(va) {
				arr.push(va);
				//$(this).validator.validate();
			}
		});
		return ( $.inArray(value, arr) == -1 );
	}, $.validator.format("名称与其它别名不能相同"));

	jQuery.validator.addMethod( "notEqualTo", function( value, element, param ) {
		return this.optional(element) || !$.validator.methods.equalTo.call( this, value, element, param );
	}, "不能相同" );

});

function activeMenu()
{
	var url = window.location.href;
	var $currMenu;
	$(".sidebar-menu ul a").each(function(){
		var $a = $(this);
		var splitArr = url.split("/");
		var href = splitArr.pop();
		if(href == $a.attr('href'))
		{
			$currMenu = $a;
			return false;
		}
	});
	
	$currMenu.parent().addClass('active');
	$currMenu.closest('ul').prev().click();;
}

function deleteRecord(href,id){
	if(confirm('您确定要删除吗？')){
		window.location.href=href+"?id="+id;
	}
}

function toogleRecord(href,id){
	if(confirm('您确定要这样操作吗？')){
		if(href.indexOf("?")>=0){
			window.location.href=href+"&id="+id;
		}else{
			window.location.href=href+"?id="+id;
		}
	}
}

var utils = {
	msg: function(title){  //提示信息
		if($(".msg").length > 0) $(".msg").remove();
		var msg = $("<div class='msg'></div>").appendTo("body");
		var windowW = $(window).outerWidth();
		msg.text(title);
		var w = windowW / 2 - msg.outerWidth() / 2;
		msg.css({"left":w + "px"});
		msg.css({"opacity":"1","z-index":"1000"});
		setTimeout(function(){
			msg.css({"opacity":"0","z-index":"-1"});
		},2500);
	},
	comdialog: function(tit,con,btntxt1,btntxt2,confirmHander,type,url,bizType){  //公用弹框
		if($(".dialogbox").length > 0) $(".dialogbox").remove();
		var con,tit,btnbox,confirm,cancel,ids=[],datas=[];
		var box = $("<div class='dialogbox'></div>").appendTo("body");
		var dialog = $("<div class='dialog'></div>").appendTo(box);
		var bg = $("<div class='dialog_bg'></div>").appendTo(box);
		tit ? $("<div class='tit'>" + tit + "</div>").appendTo(dialog) : "";
		var main = $("<div class='dialog_body'></div>").appendTo(dialog);
		con ? content = $("<div class='dialog_body_con'></div>").appendTo(main) : "";
		con? content.html(con) : "";
		(btntxt1 || btntxt2) ? btnbox =  $("<div class='btnbox'></div>").appendTo(dialog) : "";
		btntxt1 ? confirm = $("<div class='btn confirm'>" + btntxt1 + "</div>").appendTo(btnbox) : "";
		btntxt2 ? cancel = $("<div class='btn cancel'>" + btntxt2 + "</div>").appendTo(btnbox) : "";
		if(!confirm && cancel) cancel.css({"width":"92%","margin-left":"4%"});
		if(!cancel && confirm) confirm.css({"width":"92%","margin-left":"4%"});
		utils.hide($(".dialogbox .cancel"),box);
		utils.hide(bg,box);
		$(".dialogbox .confirm").on("click",function(e){
			box.hide();
			var chek = box.find(".selected");
			for(var i = 0; i < chek.length; i++){
				datas.push($(chek[i]).text());
				ids.push($(chek[i]).attr("id"));
			}
			//utils.sub(type); //一系列参数
			confirmHander(datas,ids,bizType);
			
		});
		utils.dialogChek($(".dialog .list_item"),type);
	},
	hide: function(btn,mian){  //btn:点击按钮  mian:需隐藏的选择器
		btn.on("click",function(e){  //隐藏
			mian.hide();
		});
	},
}


//时间转换方法
function get_time(date,d){
	  Y = date.getFullYear() + '-';
	  M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
	  D = date.getDate() + ' ';
	  h = date.getHours() + ':';
	  m = date.getMinutes();
	  // s = date.getSeconds(); 
	  var result;
	  if(d==false){
		  result  = Y+M+'-'+D+h+m;
	  }else{
		  result  = Y+M;
	  }
	  return result;  
	  // 输出结果：2014-04-23 18:55:49
	 }
$.fn.refreshTable = function(){
	var table = this;
	var opt = table.data('opt');
	$.loadTable(opt);
}
var tableLang = {    
	"sProcessing":"处理中...", 
	"sLengthMenu":"显示_MENU_项结果",
	"sZeroRecords":"没有匹配结果",
	"sInfo":"显示第_START_至_END_项结果，共_TOTAL_项",
	"sInfoEmpty":"显示第0至0项结果，共0项",
	"sInfoFiltered":"(由_MAX_项结果过滤)",
	"sInfoPostFix":"",
	"sSearch":"搜索:",
	"sUrl":"",
	"sEmptyTable":"表中数据为空",
	"sLoadingRecords":"载入中...",
	"sInfoThousands":",",
	"oPaginate":{ 
		"sFirst":"首页",
		"sPrevious":"上页",
		"sNext":"下页",
		"sLast":"末页"
	}, 
	"oAria":{ 
		"sSortAscending":":以升序排列此列",
		"sSortDescending":":以降序排列此列"
	}  
} 
$.loadTable = function(opt){
	var url = opt.url;
	var cols = opt.columns;
	var table = opt.table;
	table.data('opt',opt);
	var columns = new Array();
	
	$.each(opt.columns,function(){
		var column = {
			"data":	this.name
		}
		if(this.formatter)
		{
			column.formatter = this.formatter;
			column.render = function(val, type, row, meta){
				return column.formatter(val,row);
			}
		}
		columns.push(column);
	});
	var dataTable = table.dataTable({
		"language": tableLang,
		"destroy": true,
		"autoWidth": true,
		"searching": false,
		"ordering": false,
		serverSide:true,
		ajax: function (data, callback, settings) {
			var params = new Object();
			$.extend(params,opt.params,{'page':(data.start / data.length),'size':data.length})
			$.ajax({
				'url'		:	url,
				'type'		:	'GET',
				'data'		:	params,
				'dataType'	:	'json',
				'cache'		:	false,
				'success'	:	function(rtn){
					var rowdata = {
						draw			:	data.draw,
						recordsTotal	:	rtn.page.totalElements,
						recordsFiltered	:	rtn.page.totalElements,
						data 			:	rtn.page.content,
						others			:	rtn.others
					}
					settings.orginaData = rtn;
					callback(rowdata);
					//数据表为0的时候
					var _row =$("table tbody tr");
					if(_row.eq(0).find("td").html()=="表中数据为空"){
						
						$(".dataTables_length").hide();
						$(".paging_simple_numbers").hide();
						$(".dataTables_info").hide();
					}
				}
			})
		},
		columns:columns,
	});
	table.off('draw.dt').on( 'draw.dt', function (e, settings) {
	   if(opt.loadComplete)
	   {
		   opt.loadComplete(settings.orginaData)
	   }
	} );
	
}
function getVal(val,defaultVal)
{
	if(typeof(defaultVal) == 'undefined')
	{
		defaultVal = '-';
	}
	if(typeof(val) == 'undefined' || val == null)
	{
		return defaultVal;
	}
	return val;
}
$.fn.serializeJson = function(){
	var data = {};
	var array = this.serializeArray();
	$.each(array,function(){
		data[this.name]=this.value;
	})
	return data;
}
$.fn.serializeJson2 = function(){
	var data = {};
	var array = this.serializeArray();
	$.each(array,function(){
		data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
	})
	return data;
}
Date.prototype.format = function(fmt){
	var o = {
			"M+" : this.getMonth()+1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth()+3)/3),
			"S"  : this.getMilliseconds()
		  };   
		  if(/(y+)/.test(fmt))
		  {
			  fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  }
		  for(var k in o)
		  {
			  if(new RegExp("("+ k +")").test(fmt))
			  {
				  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  }
		  }
		  return fmt;   
}
