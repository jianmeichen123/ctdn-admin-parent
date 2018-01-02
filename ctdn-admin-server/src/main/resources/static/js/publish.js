//保存
$(function(){
         var num = 0;
        //表单清空
       $("#form")[0].reset();
            //表单验证
        $("#form").validate({
            submitHandler: function() {
                var title = $("input[name='title']").val();
                var reportBody = $("input[name='reportBody']").val();
                console.log('reportBody:',reportBody)
                if(!title){
                    alert('title');
                    return;
                }
                 if(num==1){
                    save();
                 }
                 if(num==2){
                    preview();
                 }
            },

        errorElement: 'label',
          /* errorPlacement: function(error, element) {
      6           *//*  error.insertAfter(element)*//*
                    error.appendTo(element.next());
      8     }*/




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
		url:'http://ctdnqa.gi.com/cloudstorage/upload/image',
		formData:{},
		headers:{
         '_uid_':getCookie('_uid_'),
         's_':getCookie('s_'),
        },
		add: function (e, data) {
            data.submit();
        },
		done: function (e, data) {
			if(data.result.success)
			{
				$("input[name='fileName']").val(data.result.uploadFiles[0].fileName)
				$("input[name='fileKey']").val(data.result.uploadFiles[0].fileUploadName)
				$("input[name='fileUrl']").val(data.result.uploadFiles[0].url)
				$("input[name='fileSize']").val(data.result.uploadFiles[0].fileLength)
                $(".pic_one img").attr('src',data.result.uploadFiles[0].url);
                $(".pic_one").css("display",'inline-block');
                $('.author-label_one').css('display','none');

			}
			else
			{
                console.log("log:",1)
			}
		}
})

$("#listPic").fileupload({
		url:'http://ctdnqa.gi.com/cloudstorage/upload/image',
		formData:{},
		headers:{
             '_uid_':getCookie('_uid_'),
             's_':getCookie('s_'),
        },
		add: function (e, data) {
			var acceptFileTypes = /\/(jpg|jpeg|png|gif)$/i;
			if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
//				$("#projLogoName").html("<font color='red'>*您的文件格式不正确</font>")
				return;
			}
			if(data.files[0].size > 500*1024*1024){
//				$("#projLogoName").html("<font color='red'>*您的文件大小超过限制，请控制在500KB之内</font>")
				return;
			}
//			if(data.file[0].name.length>200){
////				$("#projLogoName").text("文件名过长不能超过200个字...")
//				return;
//			}
//			$("#projLogoName").text("")
            data.submit();
        },
        messages: {
			maxFileSize: 'File exceeds maximum allowed size of 5b'
		},
		done: function (e, data) {
			if(data.result.success)
			{
				/*$(".picture-content img").attr("src",data.result.uploadFiles[0].url);
				$(".picture-content").show()
				$("input[name='projectLogo']").val(data.result.uploadFiles[0].url)*/
				$(".picture-big img").attr('src',data.result.uploadFiles[0].url);
                $(".picture-big").css("display",'inline-block');
                 $('.author-label_two').css('display','none');
			}
			else
			{
				$("#projLogoName").text(data.result.msg);
			}
		}
	});


/*close*/
$('.pic_one  em').click(function(){
    $(this).closest('.picture-content').css('display',"none");
});

$('.picture-big  em').click(function(){
    $(this).closest('.picture-content').css('display',"none");
});