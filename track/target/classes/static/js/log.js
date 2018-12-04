/**
 * Created with IntelliJ IDEA.
 * User: radu.miron
 * Date: 12/2/15
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
function getUser() {
    //var criteria = new Criteria();
    sendRequest("GET", "user/read/" + $('#username').val() + "/" + $('#password').val(), null, getUserSuccessHandler, getUserErrorHandler);
}

function getUserSuccessHandler(respData) {
    $("#result").append("<br>" + JSON.stringify(respData));
    //$("#result").text(respData); // appends the json to the 'result' div. see index.html
}

function getUserErrorHandler(status) {
    alert("err response: " + status); // popup on err.
}