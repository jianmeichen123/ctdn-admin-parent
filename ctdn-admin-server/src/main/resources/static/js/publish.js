//保存
$(function(){
         var num = 0;
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
                 if(num==1){
                    save();
                 }
                 if(num==2){
                    preview();
                 }
            }
        })
    //保存
   $('.publish-report').click(function(){
        num = 1;
        $("#form").submit();
   })
   //预览
   $('.preview').click(function(){
            num = 2;
           $("#form").submit();
      })
})


//保存
$.fn.serializeJson = function(){
		var data = {};
		var array = this.serializeArray();
		data["state"]=0;
		$.each(array,function(){
			data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
		})
		return data;
	}


//保存
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

//预览
function preview(){
    var url ="insertReport"
    var data = JSON.stringify($("#form").serializeJsonPre());
    $.ajax({
        type: "POST",
        url:url,
        contentType:'application/json;charset=UTF-8',
        data:data,
        success:function(data){
            if(data.message == 'OK')
            {
              $.get("previewReport",function(data){
                    window.open("http://ctdnqa.gi.com//report_detailed.html?id="+data.data.id,"_blank");
              })
            }
            else
            {
                layer.msg("保存失败~~!");
            }
        }
    })
}


//预览
$.fn.serializeJsonPre = function(){
        var data = {};
        var array = this.serializeArray();
        data["state"]=2;
        $.each(array,function(){
            data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
        })
        return data;
    }



 function getCookie(c_name)
     {
     	if (document.cookie.length>0)
     	{
     		c_start=document.cookie.indexOf(c_name + "=")
     		if (c_start!=-1)
     		{
                 c_start=c_start + c_name.length+1
                 c_end=document.cookie.indexOf(";",c_start)
                 if (c_end==-1) c_end=document.cookie.length
                 return unescape(document.cookie.substring(c_start,c_end))
             }
     	}
     	return ""
     }

//上传
$("#authorPic").fileupload({
		url:'http://127.0.0.1/cloudstorage/upload',
		formData:{},
		headers:{
         '_uid_':getCookie('_uid_'),
         's_':getCookie('s_'),
        },
		add: function (e, data) {
//			if(data.files[0].size > 25*1024*1024){
//				$("#projPbName").text("上传商业计划书，文件大小请限制在25M之内")
//				$("#projPbName").addClass("red")
//				return;
//			}
//			$("#projPbName").text("正在上传文件...")
//			$("#projPbName").removeClass("red")
            data.submit();
        },
		done: function (e, data) {
			if(data.result.success)
			{
//				$("#projPbName").removeClass("red")
//				$("#projPbName").text(data.result.uploadFiles[0].fileName);
				$("input[name='fileName']").val(data.result.uploadFiles[0].fileName)
				$("input[name='fileKey']").val(data.result.uploadFiles[0].fileUploadName)
				$("input[name='fileUrl']").val(data.result.uploadFiles[0].url)
				$("input[name='fileSize']").val(data.result.uploadFiles[0].fileLength)
			}
			else
			{
                console.log("log:",1)
			}
		}
})
