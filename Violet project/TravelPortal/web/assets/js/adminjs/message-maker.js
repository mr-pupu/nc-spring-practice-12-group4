/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function addMessage(data){
    $('#notifier').empty();
    var type = data['error'];
    var message = data['success'];
    var messageHolder = '<center><div class=\"span4\"><div class=\"alert alert-' + type + '\">\n\
<a class=\"close\" data-dismiss=\"alert\" onclick=\"$(\'notifier\').text(\'\');\">&times;</a>' + message+ '</div></div></center>';
    $('#notifier').append(messageHolder);
}

function addDynamicMessage(error, message){
    $('#notifier').empty();
    var messageHolder = '<center><div class=\"span4\"><div class=\"alert alert-' + error + '\">\n\
<a class=\"close\" data-dismiss=\"alert\" onclick=\"$(\'notifier\').text(\'\');\">&times;</a>' + message+ '</div></div></center>';
    $('#notifier').append(messageHolder);
}