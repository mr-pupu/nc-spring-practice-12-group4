/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getContextPath(){
    var loc = window.location;
    var regstr=/(.*)\/.*/  
    var aarr=regstr.exec(loc) 
    alert(aarr[1]);
    return aarr[1];
}

