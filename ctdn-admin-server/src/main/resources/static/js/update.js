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



var authorAvatar='';
var listPic='';

//更新
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

$(".preview").click(function(){
    location.href="http://ctdnqa.gi.com//report_detailed.html?id="+id;
})



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
                authorAvatar = data.result.uploadFiles[0].url;
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
				return;
			}

            data.submit();
        },
        messages: {
			maxFileSize: 'File exceeds maximum allowed size of 5b'
		},
		done: function (e, data) {
			if(data.result.success)
			{
				$(".picture-big img").attr('src',data.result.uploadFiles[0].url);
                $(".picture-big").css("display",'inline-block');
                 $('.author-label_two').css('display','none');
                 listPic = data.result.uploadFiles[0].url;
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
    $('.author-label_one').css('display','inline-block');
});

$('.picture-big  em').click(function(){
    $(this).closest('.picture-content').css('display',"none");
     $('.author-label_two').css('display','inline-block');
});

$.fn.serializeJson = function(){
		var data = {};
		var array = this.serializeArray();
		data["state"]=0;
		data["id"]=id;
		data["authorAvatar"] = authorAvatar;
        data["listPic"] = listPic;
        alert(data["listPic"] )
		$.each(array,function(){
			data[this.name]=this.value.replace(/(^\s+)|(\s+$)/g,"");
		})
		return data;
	}