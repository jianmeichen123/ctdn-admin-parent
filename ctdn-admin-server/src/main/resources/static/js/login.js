$( function(){
    $(".back-login-tips").hide();

    $(".to-login").click(function(event){
        event.preventDefault();
        var login = $("#login").val();
        var password = $("#password").val();

        if(login&&password){
             $(".empty").hide();
         }
        if(!login&&!password){
            $(".empty").show();
        }
        $.post("login",{
            "login":login,
            "password":password
        },function(data){
            if(data.data)
            {
                window.location.href = "index";
            }
            else
            {
                //$("#alert").text("").append(data.message).attr("class","alert alert-danger");
                $(".back-login-tips").show();
            }
        });
    });

     $("body").keydown(function(event) {
         if (event.keyCode == "13") {//keyCode=13是回车键
             $('.to-login').click();
         }
     });
})


//$(".to-login").click(function(){
//        var login =$(".login").val();
//        var password=$(".password").val();
//        if(login&&password){
//                $(".empty").hide();
//                sendGetRequest("http://127.0.0.1:8099/fms/login",function(data){
//                    if(!data){
//                        $(".back-login-tips").show();
//                        $(".empty").hide();
//                    }
//                    console.log("data:",data)
//                })
//        }else{
//            $(".empty").show();
//        }
// })