/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   
   $(document).on('shown.bs.modal', '#myModal', function(e){
      $('.error').empty(); 
      $('.reg-input').val('');
   });
   
   $(document).on('click', '#registerUser', function(e){

       e.preventDefault();
       var password = $('#reg-password').val();
       var confpassword = $('#reg-confirmpassword').val();
       if(password!== confpassword){
           $('#password-error').append('<p>*Your passwords did not match</p>');
           $('#reg-password').val('');
           $('#reg-confirmpassword').val('');
           return;
       }
      $.ajax({
          url:'user/register',
          type: 'POST',
          data: JSON.stringify({
              firstName: $('#reg-first-name').val(),
              lastName: $('#reg-last-name').val(),
              username: $('#reg-user-name').val(),
              email: $('#reg-email').val(),
              password: password
          }),
          dataType: "json",
          headers:{
              'Content-Type': 'application/json',
              'Accept' : 'application/json'
          },
          success: function(data,status){
              $('#myModal').toggle();
              $('.errors').empty();
          },
          error: function(data,status){
              var errors = data.responseJSON.validationErrors;
              $.each(errors, function(i, error){
                 switch(error.field){
                     case 'password': $('#password-error').empty(); $('#password-error').append('<p>Password ' + error.error+ '</p>');
                         break;
                     case 'email': $('#reg-email-error').empty(); $('#reg-email-error').append('<p>' + error.error+ '</p>');
                         break;
                     case 'firstName': $('#reg-first-name-error').empty(); $('#reg-first-name-error').append('<p>' + error.error+ '</p>');
                         break;
                     case 'lastName': $('#reg-last-name-error').empty(); $('#reg-last-name-error').append('<p>' + error.error+ '</p>');
                         break;
                     case 'username':$('#reg-user-name-error').empty(); $('#reg-user-name-error').append('<p>' + error.error+ '</p>');
                         break;
                         
                 } 
              });
              console.log(errors);
              
          }
          
      }); 
   });
   
    
});

