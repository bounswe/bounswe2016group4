/**
 * Created by alper on 20/11/2016.
 */
$(document).ready(function () {
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
                        html = html + "<a href='#' class='list-group-item'>" +
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
                        html = html + "<a href='#' class='list-group-item'>" +
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

    $(".food-ing").on('change', function () {
        $(this).next().text($(this).val());
    });

    $("#adv-search-submit").click(function () {

    });
});