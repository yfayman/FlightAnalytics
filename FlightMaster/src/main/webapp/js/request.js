/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    $(document).on('click','#submit-requeset', function(e){
        $.ajax({
            url:'reqeust/submit',
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify({
                
            }),
            headers: {
                'Content-Type' : 'application/json',
                'Accept' : 'application/json'
            },
            success : function(data,status){
                
            },
            error :function(data,status){
                
            }
            
        });
    });
    
});