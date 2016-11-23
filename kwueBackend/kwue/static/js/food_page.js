/**
 * Created by alper on 23/11/2016.
 */
$(document).ready(function () {
    $("#mark-as-eaten").click(function () {
        var food_id = $(this).data('foodid');
        var user_id = 1;
        $.ajax({
            url: 'mark_as_eaten',
            data: {
                user_id: user_id,
                food_id: food_id
            },
            success: function () {
                console.log("num num num...");
            }
        });
    });

    $("#more-button").click(function () {
        var toggle = $(this).text();
        if(toggle == "More...") {
            $("#others").show();
            $(this).text("Less");
        } else {
            $("#others").hide();
            $(this).text("More...");
        }
    });
});