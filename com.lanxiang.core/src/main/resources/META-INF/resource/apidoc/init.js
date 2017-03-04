var pathArray = window.location.pathname.split('/')
console.log("Path Info");
console.log(pathArray);
pathArray.pop();
pathArray.pop();
console.log(pathArray);
var basePath = pathArray.join("/");
swaggerUrl = basePath + ("/apidoc/json");
console.log("Base Path");
console.log(basePath);
console.log(swaggerUrl);

$(document).ready(function () {
    // $.getJSON(basePath + "/profile", function (data) {
    //     setUserInfo(data);
    // }).fail(function () {
    //     unsetUserInfo();
    // });
    //
    // $('#loginForm').on('submit', function login() {
    //     var data = {};
    //     data.username = $('#username').val();
    //     data.password = $('#password').val();
    //     var jsonStr = JSON.stringify(data);
    //     $.ajax({
    //         type: "POST",
    //         contentType: 'application/json',
    //         url: basePath + "/internal/crm/login",
    //         data: jsonStr,
    //         dataType: "json",
    //         success: function (data, status) {
    //             setUserInfo(data);
    //         },
    //         error: function (xhr) {
    //             var errorInfo = $.parseJSON(xhr.responseText);
    //             alert(errorInfo.message);
    //         }
    //     });
    //     return false;
    // });
    //
    // $('#logoutBtn').on('click', function () {
    //     $.getJSON(basePath + "/profile/logout", function (data, status) {
    //         unsetUserInfo(data);
    //     });
    // });
    //
    // function setUserInfo(data) {
    //     var label = data.fullname ? data.fullname : "";
    //     label = label + "(id=" + data.id;
    //     if (data.name) {
    //         label = label + ",name=";
    //         label = label + data.name;
    //     }
    //     if (data.mobile) {
    //         label = label + ", mobile=";
    //         label = label + data.mobile;
    //     }
    //     label = label + ")";
    //     if (data.organization) {
    //         label = label + "@" + data.organization.fullname;
    //         label = label + "(id=" + data.organization.id;
    //         if (data.organization.name) {
    //             label = label + ", name=";
    //             label = label + data.organization.name;
    //         }
    //         label = label + ")";
    //     }
    //     $('#userLabel').html(label);
    //     $('#logoutPanel').show();
    //     $('#loginPanel').hide();
    // }
    //
    // function unsetUserInfo() {
    //     $('#userLabel').html("未登录");
    //     $('#logoutPanel').hide();
    //     $('#loginPanel').show();
    // }

});