$(function(){
   $('.publish-report-span').click(function(){
        var url ="insertReport"
        var data = JSON.stringify($("#form").serializeJson());
        $.ajax({
            type: "POST",
            url:url,
            contentType:'application/json;charset=UTF-8',
            data:data,
            success:function(data){
                if(data.message == 'OK')
                {
                    layer.msg("保存成功,我们会尽快联系您~",0,function(){
                        location.href="index"
                    });

                }
                else
                {
                    layer.msg("保存失败,请稍后再试!");
                }
            }
        })

//        var title=JSON.stringify("")
//        $.post("insertReport",{"title":title},function(data){})
   })
})

$.fn.serializeJson = function(){
		var data = {};
		var array = this.serializeArray();
		$.each(array,function(){
			data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
		})
		return data;
	}


