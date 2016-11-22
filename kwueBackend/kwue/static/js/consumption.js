/**
 * Created by alper on 22/11/2016.
 */
$(document).ready(function () {

    // default setting
    var setting = "daily";

    $.ajax({
        url: 'get_consumption_history',
        data: {
            user_id: '1',
            setting: setting
        },
        success: function (response) {
            var foods = response['foods'];
            var nutritions = response['nutritional_values_dict'];
            var html_foods = "";
            var html_nutritions = "";
            for(i=0; i<foods.length; i++) {
                html_foods = html_foods + "<div class='list-group-item'><h4><p>"+ foods[i]['food_name'] +"</p></h4>" +
                        "<img src='"+ foods[i]['food_image'] +"' style='width: 100px; height: auto'></div>";
            }
            $("#consumption-panel").html(html_foods);
            for (var key in nutritions) {
                var value = nutritions[key];
                html_nutritions = html_nutritions + "<p><strong>" + key + "</strong>: " + value + "</p>";
            }
            $("#nutrition-panel").html(html_nutritions);
        }
    });

    $(".history-setting").click(function () {
        var el = $(this);
        var setting = el.attr('id');
        $(".active").removeClass("active");
        el.parent().addClass("active");

        $.ajax({
            url: 'get_consumption_history',
            data: {
                user_id: '1',
                setting: setting
            },
            success: function (response) {
                var foods = response['foods'];
                var nutritions = response['nutritional_values_dict'];
                var html_foods = "";
                var html_nutritions = "";
                for(i=0; i<foods.length; i++) {
                    html_foods = html_foods + "<div class='list-group-item'><h4><p>"+ foods[i]['food_name'] +"</p></h4>" +
                            "<img src='"+ foods[i]['food_image'] +"' style='width: 100px; height: auto'></div>";
                }
                $("#consumption-panel").html(html_foods);
                for (var key in nutritions) {
                    var value = nutritions[key];
                    html_nutritions = html_nutritions + "<p><strong>" + key + "</strong>: " + value + "</p>";
                }
                $("#nutrition-panel").html(html_nutritions);
            }
        });
    });
});