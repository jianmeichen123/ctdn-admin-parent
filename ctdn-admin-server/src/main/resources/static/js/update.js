function fillUpdateReport(data,divList){
    $(divList).each(function(){
        var div = $(this)
        var ls = div.find("*[data-field]");
        $(ls).each(function(){
            var o = $(this)
            var k = o.attr("data-field");
            var v = data[k]
            if(v){
                o.html(v)
                o.val(v);
            }
        })
    })
}

var id = getHrefParamter("id");
sendGetRequest("getReport/"+id,function(data){fillUpdateReport(data.data,$("div[data-query='getReport']"))})

$(function(){
//        //表单清空
//       $("#form")[0].reset();
//            //表单验证
//        $("#form").validate({
//            submitHandler: function() {
//                var title = $("input[name='title']").val();
//                if(!title){
//                    $(".publish-title").addClass("red")
//                    return;
//                }
//
//            }
//        })
   $('.publish-report').click(function(){
//        $("#form").submit();
        update();
   })
})

$.fn.serializeJson = function(){
		var data = {};
		var array = this.serializeArray();
		data["state"]=0;
		data["id"]=id;
		console.log(data)
		$.each(array,function(){
			data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
		})
		return data;
	}

function update(){
    var url ="updateReport"
    var data = JSON.stringify($("#form").serializeJson());
    $.ajax({
        type: "POST",
        url:url,
        contentType:'application/json;charset=UTF-8',
        data:data,
        success:function(data){
            if(data.message == "OK")
            {
                layer.msg("修改成功~",{time:1000},function(data){
                    location.href="index"
                });
            }
            else
            {
                layer.msg("修改失败~~!");
            }
        }
    })
}