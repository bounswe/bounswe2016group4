/**
 * Created by alper on 20/11/2016.
 */
$(document).ready(function () {
    $("#search-btn").click(function () {
        var query = $("#search-bar").val();
        $.ajax({
            url: "basic_search",
            data: {
                user_id: '0',
                search_text: query
            },
            success: function (result) {
                $("#food-results").html(result['food_set']);
                $("#server-results").html(result['food_server_set']);
                $("#sem-food-results").html(result['semantic_foods']);
                $("#sem-server-results").html(result['semantic_users']);
            }
        });
        $(".result-bar").show();
        $("#featured-foods").hide();
    });
    
    $("#adv-search-btn").click(function () {
        $(".result-bar").hide();
        $("#featured-foods").show();
    })
});