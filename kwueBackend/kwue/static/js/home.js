/**
 * Created by alper on 20/11/2016.
 */
$(document).ready(function () {

    var user_id = 1;

    $.ajax({
        url: 'get_eating_preferences',
        data: {
            user_id: user_id,
        },
        success: function (response) {
            var protein_bound = [response['protein_lower_bound'], response['protein_upper_bound']];
            var fat_bound = [];
            var carbohydrate_bound = [];
            var calorie_bound = [];
            var sugar_bound = [];
        }
    });

    var slide_pro = $("#protein-slider").slider({ id: "protein", min: 0, max: 1000, range: true, tooltip: 'hide' });
    var slide_fat = $("#fat-slider").slider({ id: "fat", min: 0, max: 1000, range: true, value: [250, 750], tooltip: 'hide' });
    var slide_carbo = $("#carbohydrate-slider").slider({ id: "carbohydrate", min: 0, max: 1000, range: true, value: [250, 750], tooltip: 'hide' });
    var slide_cal = $("#calorie-slider").slider({ id: "calorie", min: 0, max: 1000, range: true, value: [250, 750], tooltip: 'hide' });
    var slide_sugar = $("#sugar-slider").slider({ id: "sugar", min: 0, max: 1000, range: true, value: [250, 750], tooltip: 'hide' });

    $(".slider").on("slide", function (slideEvt) {
        $(this).next().text(slideEvt.value[0] + "-" + slideEvt.value[1]);
    });


    $("#search-btn").click(function () {
        var query = $("#search-bar").val();
        $.ajax({
            url: "basic_search",
            data: {
                user_id: '1',
                search_text: query
            },
            success: function (result) {
                $("#food-results").html(function () {
                    var response = result['food_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='get__food?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
                            "<p>Food Name: " + response[i]['food_name'] + "</p>" +
                            "<p><img src='"+ response[i]['food_image'] +"' style='width: 100px; height: auto'></p>" +
                            "<p>Calorie Value: " + response[i]['calorie_value'] + "</p></a>";
                    }
                    return html;
                });
                $("#user-results").html(function () {
                    var response = result['user_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='#' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
                $("#sem-food-results").html(function () {
                    var response = result['semantic_food_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='get__food?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
                            "<p>Food Name: " + response[i]['food_name'] + "</p>" +
                            "<p><img src='"+ response[i]['food_image'] +"' style='width: 100px; height: auto'></p>" +
                            "<p>Calorie Value: " + response[i]['calorie_value'] + "</p></a>";
                    }
                    return html;
                });
                $("#sem-user-results").html(function () {
                    var response = result['semantic_user_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='#' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
            }
        });
        $(".result-bar").show();
        $("#featured-foods").hide();
    });
    
    $("#adv-search-btn").click(function () {
        $(".result-bar").hide();
        $("#featured-foods").show();
        $("#adv-search-bar").toggle();


    });

    $("#adv-search-submit").click(function () {
        var x = $("#advanced-search-form").serializeArray();
        console.log(x);
    });
});