/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getContextPath(){
    var loc = window.location;
    var regstr=/(.*)\/.*/  
    var aarr=regstr.exec(loc) 
    return aarr[1];
}

