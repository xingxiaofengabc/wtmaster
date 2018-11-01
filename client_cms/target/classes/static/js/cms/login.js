function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    //var params = "username="+username+"&amp;password="+password;
    $.ajax({
        url : '/login',
        type:'POST',
        dataType:"json",
        data : {"username":username,"password":password},
        success : function(data){
            console.log("data:"+data);
            if(data.code == 200){
                console.log("data:"+data.token);
                var token = data.token;
                // web storage的查看 - 在浏览器的开发者面板中的application中查看。
                // local storage - 本地存储的数据。 长期有效的。
                // session storage - 会话存储的数据。 一次会话有效。
                var localStorage = window.localStorage; // 浏览器提供的存储空间。 根据key-value存储数据。
                localStorage.token = token;
                window.location.href="../index"
            }else{
                alert(data.msg);
            }
        }
    });
}

function setHeader(xhr){ // XmlHttpRequest
    console.log(window.localStorage.token);
    xhr.setRequestHeader("authtoken",window.localStorage.token);
}
function setComplete(xhr) {
    var localStorage = window.localStorage; // 浏览器提供的存储空间。 根据key-value存储数据。
    console.log(xhr.token);
    localStorage.token = token;
    console.log("请求完成 。。。。.")
}
function testLocalStorage(){
    $.ajax({
        'url' : '/testAll',
        'success' : function(data){
            /*     if(data.code == 200){
                     window.localStorage.token = data.token;
                     alert(data.data);
                 }else{
                     alert(data.msg);
                 }*/
            alert(data);
        },
        'beforeSend' : setHeader,
        'complete':setComplete
    });
}