/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    $(document).on('click','#submit-request', function(e){
        
        console.log("submit request triggered");
        
        $.ajax({
            url:'request/add',
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify({
                origin:$('#departingLoc').val(),
                depDate: dateToUnixTime($('#departingDate').val()),
                destination: $('#arrivingLoc').val(),
                retDate : dateToUnixTime($('#arrivingDate').val()),
                maxStops : $('#max-stops').val(),
                adultPassengers: $('#adult-passengers').val(),
                childPassengers: $('#child-passengers').val(),
                numberQueries: $('#numberQueries').val(),
                interval :$('#interval').val()
                
                
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

function dateToUnixTime(dateInput) {
    if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
        return dateInput;
    } else {
        var dateNum = [];
        dateNum = dateInput.split(/[-/]/);
        var f = new Date(dateNum[2], dateNum[0] - 1, dateNum[1]);
        return f.getTime();
    }
}

var airports = [];
// Populates airports with json from server
(function(){
    $.ajax({
        url: "/FlightMaster/request/airportData",
        method : "GET",
        dataType: 'json',
      //  async: false,
        success : function(data){
            for(var i = 0; i < data.iataCodes.length; i++){
                airports.push(data.iataCodes[i]);
            }
           
        },
        error: function(data,status){
            console.log("Fail");
        },
        headers: {
            "Accepts" : "application/json"
        }
    });
})();
