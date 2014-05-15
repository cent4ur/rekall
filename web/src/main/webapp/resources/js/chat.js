var wsUri = 'ws://' + document.location.host + '/rekall-web/chat-websocket';
console.log(wsUri);
var chatSocket = new WebSocket(wsUri);

var username;
chatSocket.onopen = function(evt) { onOpen(evt); };
chatSocket.onmessage = function(evt) { onMessage(evt); };
chatSocket.onerror = function(evt) { onError(evt); };
chatSocket.onclose = function(evt) { onClose(evt); };
var textField = document.getElementById("textField");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");
chatlog.scrollTop = chatlog.scrollHeight;

function join() {
    chatSocket.send(username + " joined");
}

function join(usernamex) {
    chatSocket.send(usernamex + " joined");
}

function send_message() {
    chatSocket.send(username + ": " + textField.value);
}

function onMessage(evt) {
    if (evt.data.indexOf("joined") !== -1) {
        users.innerHTML += evt.data.substring(0, evt.data.indexOf(" joined")) + "\n";
    } else {
        chatlog.innerHTML += evt.data + "\n";
    }
}

function onError(evt) {
}

function disconnect() {
    chatSocket.close();
}
