/**
 * Created with IntelliJ IDEA.
 * User: maria.moldovan
 * Date: 18/11/18
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
function getNewUser() {

     var username = $('#username').val();
     var passwordA = $('#password').val();
     var repPass = $('#repPassword').val();

     var userJson = {
        password: "",
        userName: ""
     }
     if (password.length > 0) {

        if (repPass.length > 0 && password == repPass) {
            userJson = {
                        password : passwordA,
                        userName : username };
            console.log(userJson);
            sendRequest("POST", "user/create", JSON.stringify(userJson), getNewUserSuccessHandler, getNewUserErrorHandler);
        }
        else{
            getNewUserErrorHandler();
        }
     }

}

function getNewUserSuccessHandler(respData) {
    $("#result").append("<br>" + JSON.stringify(respData));
    //$("#result").text(respData); // appends the json to the 'result' div. see index.html
}

function getNewUserErrorHandler(status) {
    alert("err response: " + status); // popup on err.
}