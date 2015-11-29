/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var chart;

// Sends a request to server for data and then updates chart
function getFlightData(requestId) {
    var min = ['min'];
    var max = ['max'];
    var average = ['ave'];
    var xAxis = ['x'];
    $.ajax({
        url: "currentrequest/" + requestId,
        method: 'GET',
        dataType: 'json',
        headers: {
            'Accept': 'application/json'
        },
        success: function (data, status) {
            function pushDataToArrays() {
                min.push(queryMin);
                max.push(queryMax);
                average.push(Math.round((querySum / queryCount + .00001) * 100) / 100);
            }

            if (data === null || data.length === 0)
                return;
            var queryMin = data[0].price;
            var queryMax = data[0].price;
            var querySum = 0;
            var queryCount = 0;

            var timeMarker = data[0].queryTime;
            xAxis.push(new Date(timeMarker));
            /*
             * Sort data in ascending order by queryTime. This clusters all the
             * data points from each query to allow fo the for loop to calculate
             * min/max/average efficiently
             */
            data.sort(function (a, b) {
                return a.queryTime - b.queryTime;
            });

            for (var i = 0; i < data.length; i++) {
                //if the program hits a new time set
                if (data[i].queryTime !== timeMarker) {
                    timeMarker = data[i].queryTime;
                    xAxis.push(new Date(timeMarker));
                    pushDataToArrays();
                    queryMin = data[i].price;
                    queryMax = data[i].price;
                    querySum = 0;
                    queryCount = 0;

                }
                if (queryMin > data[i].price)
                    queryMin = data[i].price;
                if (queryMax < data[i].price)
                    queryMax = data[i].price;
                querySum += data[i].price;
                queryCount++;
                if (i === data.length - 1) {
                    pushDataToArrays();
                }
            }

            chart.load({
                columns: [xAxis, min, max, average]
            });
        },
        error: function (data, status) {
            console.log("Error getting flight data");
        }

    });

}

//Converts an id tag to just the numerical portion
function getRequestIdFromTag(tag) {
    return tag.split('-')[1];
}

(function () {
    var idTag = $('.request-button')[0].id;
    var id = getRequestIdFromTag(idTag);

    chart = c3.generate({
        data: {
            bindto: '#chart',
            x: 'x',
//        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            columns: [
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%Y-%m-%d %H:%M:%S'
                },
                label: {
                    text: 'Time',
                    position: 'outer-center'
                },
                padding: {
                    left: 0,
                    right: 0
                }

            },
            y: {
                label: {
                    text: 'Price',
                    position: 'outer-middle'
                }
            }
        }
    });
    getFlightData(id);
})();

$(document).ready(function () {
    $(document).on('click', '.request-button', function (e) {
        var button = e.target;
        var idTag = button.id;
        // Remove current active
        $('button.active').removeClass('active');
        //Add new current active
        $('#' + idTag).addClass('active');
        getFlightData(getRequestIdFromTag(idTag));
    });
});