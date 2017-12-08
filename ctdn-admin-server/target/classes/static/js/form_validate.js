
		$(function(){
			$("#login_form").validate();
			$("#change_password").validate();
			$("#loginbtn").click(function(event){
				event.preventDefault();
				if(!$("#login_form").validate().form())
				{
					return;
				}
				var username = $("#username").val();
				var password = $("#password").val();
				
				$.post("login",{
					"login":username,
					"password":password
				},function(data){
					if(data.code == 'OK')
					{
					    //admin
					    if(data.value.login=="admin"||data.value.login=="data-admin"){
                              window.location.href = "index.html";
					    }else{
					        $('#login_form').hide();
                            var loginUser = data.value;
                            var companyGroupList = loginUser.companyGroupList;
                            if(companyGroupList.length==1){
                                setGroup(companyGroupList[0].id,companyGroupList[0].title)
                            }else{
                                var html=""
                                $('#group_in').show();
                                $.each(companyGroupList,function(){
                                    html+= "<option value='"+$(this)[0].id+"'>"+$(this)[0].title+"</option>";
                                })
                                $("#group").html(html)
                            }
					    }
					}
					else
					{
						alert(data.message);
					}
				});
			});
			
			
			 $("body").keydown(function(event) {
	             if (event.keyCode == "13") {//keyCode=13是回车键
	                 $('#loginbtn').click();
	             }
	         });

	         $("#group_click").click(function(event){
	            var id = $("#group").val();
	            var title = $("#group").find("option:selected").text();
	            setGroup(id,title)
		     })

		     function setGroup(id,title){
		         var url = "../fms/userGroup/setGroup/"+id+"/"+title;
                 $.ajax({
                    url:url,
                    type:'GET',
                    cache: false,
                    success:function(){
                        window.location.href = "index.html";
                    }
                })
		     }
		})