$(function(){
        //表单清空
       $("#form")[0].reset();
            //表单验证
        $("#form").validate({
            submitHandler: function() {
                var title = $("input[name='title']").val();
                if(!title){
                    $(".publish-title").addClass("red")
                    return;
                }
             save();
            }
        })
   $('.publish-report').click(function(){
        $("#form").submit();
   })
})

$.fn.serializeJson = function(){
		var data = {};
		var array = this.serializeArray();
		data["state"]=0;
		$.each(array,function(){
			data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
		})
		return data;
	}

function save(){
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
                layer.msg("保存成功~",{time:1000},function(data){
                    location.href="index"
                });
            }
            else
            {
                layer.msg("保存失败~~!");
            }
        }
    })
}

