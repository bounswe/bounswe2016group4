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
                $("#food-results").html(result['food_set']);
                $("#server-results").html(result['food_server_set']);
                $("#sem-food-results").html(result['semantic_food_set'][0]);
                $("#sem-server-results").html(result['semantic_user_set'][0]['user_name']);
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