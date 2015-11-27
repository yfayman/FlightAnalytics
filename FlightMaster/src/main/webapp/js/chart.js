/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $(document).on('click', '.request-button', function(e){
        var button = e.target;
        var id = button.id;
        // Remove current active
        $('button.active').removeClass('active');
        //Add new current active
        $('#'+id).addClass('active');
        
    });
});