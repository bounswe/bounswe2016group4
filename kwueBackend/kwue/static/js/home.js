/**
 * Created by alper on 20/11/2016.
 */
$(document).ready(function () {

    var user_id = 1;
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

    var consumed_today = [];

    $.ajax({
        url: 'get_consumption_history',
        data: {
            setting: 'daily'
        },
        success: function (response) {
            var nutritions = response['nutritional_values_dict'];
            consumed_today = [nutritions['carbohydrate_value'],
                                nutritions['sugar_value'],
                                nutritions['fat_value'],
                                nutritions['protein_value'],
                                nutritions['fiber_value']];

            var data = {
                labels: ["Carbohydrate", "Sugar", "Fat", "Protein", "Fiber"],
                datasets: [
                        {
                            label: "Suggested amount of nutritions",
                            backgroundColor: "rgba(0, 230, 226, 0.1)",
                            borderColor: "rgba(0, 230, 226, 1)",
                            pointBackgroundColor: "rgba(0, 230, 226, 1)",
                            pointBorderColor: "#fff",
                            pointHoverBackgroundColor: "#fff",
                            pointHoverBorderColor: "rgba(0, 230, 226, 1)",
                            data: [300, 30, 65, 50, 25]
                        },
                        {
                            label: "Amount consumed today",
                            backgroundColor: "rgba(255,99,132,0.2)",
                            borderColor: "rgba(255,99,132,1)",
                            pointBackgroundColor: "rgba(255,99,132,1)",
                            pointBorderColor: "#fff",
                            pointHoverBackgroundColor: "#fff",
                            pointHoverBorderColor: "rgba(255,99,132,1)",
                            data: consumed_today
                        }
                ]
            };

            var context = document.getElementById("comparative");
            var myRadarChart = new Chart(context, {
                type: 'radar',
                data: data
            });
        }
    });

    $.ajax({
        url: 'get_eating_preferences',
        data: {
            user_id: user_id,
        },
        success: function (response) {
            protein_bound = [response['protein_lower_bound'], response['protein_upper_bound']];
            fat_bound = [response['fat_lower_bound'], response['fat_upper_bound']];
            carbohydrate_bound = [response['carbohydrate_lower_bound'], response['carbohydrate_upper_bound']];
            calorie_bound = [response['calorie_lower_bound'], response['calorie_upper_bound']];
            sugar_bound = [response['sugar_lower_bound'], response['sugar_upper_bound']];
            slide_pro = $("#protein-slider").slider({ id: "protein", min: 0, max: 1000, range: true, value: protein_bound, tooltip: 'hide' });
            slide_fat = $("#fat-slider").slider({ id: "fat", min: 0, max: 1000, range: true, value: fat_bound, tooltip: 'hide' });
            slide_carbo = $("#carbohydrate-slider").slider({ id: "carbohydrate", min: 0, max: 1000, range: true, value: carbohydrate_bound, tooltip: 'hide' });
            slide_cal = $("#calorie-slider").slider({ id: "calorie", min: 0, max: 1000, range: true, value: calorie_bound, tooltip: 'hide' });
            slide_sugar = $("#sugar-slider").slider({ id: "sugar", min: 0, max: 1000, range: true, value: sugar_bound, tooltip: 'hide' });

            $("#protein-amount").text(protein_bound[0]+"/"+protein_bound[1]);
            $("#fat-amount").text(fat_bound[0] + "/" + fat_bound[1]);
            $("#carbo-amount").text(carbohydrate_bound[0] + "/" + carbohydrate_bound[1]);
            $("#cal-amount").text(calorie_bound[0] + "/" + calorie_bound[1]);
            $("#sugar-amount").text(sugar_bound[0] + "/" + sugar_bound[1]);
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
            $("#wanted").val(wantedtext);
            $("#unwanted").val(unwantedtext);


            slide_pro.on("slide", function (slideEvt) {
                $("#protein-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_fat.on("slide", function (slideEvt) {
                $("#fat-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_carbo.on("slide", function (slideEvt) {
                $("#carbo-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_cal.on("slide", function (slideEvt) {
                $("#cal-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
            slide_sugar.on("slide", function (slideEvt) {
                $("#sugar-amount").text(slideEvt.value[0] + "/" + slideEvt.value[1]);
            });
        }
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
                        html = html + "<a href='food_page?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
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
                        html = html + "<a href='user_profile_page?user_id="+ response[i]['user_id'] +"' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
                $("#sem-food-results").html(function () {
                    var response = result['semantic_food_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='food_page?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
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
                        html = html + "<a href='user_profile_page?user_id="+ response[i]['user_id'] +"' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
            }
        });
        $(".result-bar").show();
        $(".home").hide();
        $("#comparative").hide();
    });
    
    $("#adv-search-btn").click(function () {
        $(".result-bar").hide();
        $(".home").show();
        $("#comparative").show();
        $("#adv-search-bar").toggle();
    });

    $("#adv-search-submit").click(function () {
        var search = $("#search-bar").val();
        var protein = $("#protein-amount").text().split("/");
        var fat = $("#fat-amount").text().split("/");
        var carbohydrate= $("#carbo-amount").text().split("/");
        var calorie = $("#cal-amount").text().split("/");
        var sugar = $("#sugar-amount").text().split("/");
        var wantedList = $("#wanted").val().split(",");
        var unwantedList = $("#unwanted").val().split(",");
        $.ajax({
            url: 'advanced_search',
            data: {
                search_text: search,
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
                $("#food-results").html(function () {
                    var response = result['food_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='food_page?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
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
                        html = html + "<a href='user_profile_page?user_id="+ response[i]['user_id'] + "' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
                $("#sem-food-results").html(function () {
                    var response = result['semantic_food_set'];
                    var html = "";
                    for(i=0; i<response.length; i++) {
                        html = html + "<a href='food_page?food_id="+ response[i]['food_id'] +"' class='list-group-item'>" +
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
                        html = html + "<a href='user_profile_page?user_id="+ response[i]['user_id'] +"' class='list-group-item'>" +
                            "<p>User Name: " + response[i]['user_name'] + "</p>" +
                            "<p><img src='"+ response[i]['user_image'] +"' style='width: 100px; height: auto'></p></a>";
                    }
                    return html;
                });
            }
        });
        $(".result-bar").show();
        $(".home").hide();
        $("#comparative").hide();
    });
});