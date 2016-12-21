/**
 * Created by alper on 21/12/2016.
 */
$(document).ready(function () {
    var protein_bound = [0,1000];
    var fat_bound = [0,1000];
    var carbohydrate_bound = [0,1000];
    var calorie_bound = [0,1000];
    var sugar_bound = [0,1000];
    var slide_pro;
    var slide_fat;
    var slide_carbo;
    var slide_cal;
    var slide_sugar;

    $.ajax({
        url: 'get_eating_preferences',
        data: {
            user_id: 1,
        },
        success: function (response) {
            protein_bound = [response['protein_lower_bound'], response['protein_upper_bound']];
            fat_bound = [response['fat_lower_bound'], response['fat_upper_bound']];
            carbohydrate_bound = [response['carbohydrate_lower_bound'], response['carbohydrate_upper_bound']];
            calorie_bound = [response['calorie_lower_bound'], response['calorie_upper_bound']];
            sugar_bound = [response['sugar_lower_bound'], response['sugar_upper_bound']];
            slide_pro = $("#new-protein-slider").slider({ id: "protein", min: 0, max: 3000, range: true, value: protein_bound, tooltip: 'hide' });
            slide_fat = $("#new-fat-slider").slider({ id: "fat", min: 0, max: 3000, range: true, value: fat_bound, tooltip: 'hide' });
            slide_carbo = $("#new-carbohydrate-slider").slider({ id: "carbohydrate", min: 0, max: 3000, range: true, value: carbohydrate_bound, tooltip: 'hide' });
            slide_cal = $("#new-calorie-slider").slider({ id: "calorie", min: 0, max: 3000, range: true, value: calorie_bound, tooltip: 'hide' });
            slide_sugar = $("#new-sugar-slider").slider({ id: "sugar", min: 0, max: 3000, range: true, value: sugar_bound, tooltip: 'hide' });

            $("#new-protein-amount").text(protein_bound[0]+"/"+protein_bound[1]);
            $("#new-fat-amount").text(fat_bound[0] + "/" + fat_bound[1]);
            $("#new-carbo-amount").text(carbohydrate_bound[0] + "/" + carbohydrate_bound[1]);
            $("#new-cal-amount").text(calorie_bound[0] + "/" + calorie_bound[1]);
            $("#new-sugar-amount").text(sugar_bound[0] + "/" + sugar_bound[1]);
            var wantedtext = "";
            var unwantedtext = "";
            for(i=0; i<response['wanted_list'].length; i++) {
                wantedtext = wantedtext + response['wanted_list'][i] + ",";
            }
            for(i=0; i<response['unwanted_list'].length; i++) {
                unwantedtext = unwantedtext + response['unwanted_list'][i] + ",";
            }
            wantedtext = wantedtext.substring(0, wantedtext.length-1);
            unwantedtext = unwantedtext.substring(0, unwantedtext.length-1);
            $("#new-wanted").val(wantedtext);
            $("#new-unwanted").val(unwantedtext);


            slide_pro.on("slide", function (slideEvt) {
                $("#new-protein-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_fat.on("slide", function (slideEvt) {
                $("#new-fat-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_carbo.on("slide", function (slideEvt) {
                $("#new-carbo-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_cal.on("slide", function (slideEvt) {
                $("#new-cal-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_sugar.on("slide", function (slideEvt) {
                $("#new-sugar-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
        }
    });

    $("#submit-diet").click(function () {
        var protein = $("#new-protein-amount").text().split("/");
        var fat = $("#new-fat-amount").text().split("/");
        var carbohydrate= $("#new-carbo-amount").text().split("/");
        var calorie = $("#new-cal-amount").text().split("/");
        var sugar = $("#new-sugar-amount").text().split("/");
        var wantedList = $("#new-wanted").val().split(",");
        var unwantedList = $("#new-unwanted").val().split(",");
        $.ajax({
            url: 'update_eating_preferences',
            method: 'post',
            data: {
                user_id: 1,
                protein_lower_bound: protein[0],
                protein_upper_bound: protein[1],
                fat_lower_bound: fat[0],
                fat_upper_bound: fat[1],
                carbohydrate_lower_bound: carbohydrate[0],
                carbohydrate_upper_bound: carbohydrate[1],
                calorie_lower_bound: calorie[0],
                calorie_upper_bound: calorie[1],
                sugar_lower_bound: sugar[0],
                sugar_upper_bound: sugar[1],
                wanted_list: JSON.stringify(wantedList),
                unwanted_list: JSON.stringify(unwantedList)
            },
            success: function (result) {
                console.log(result);
            }
        });
    });

});