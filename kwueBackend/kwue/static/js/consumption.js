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
            var daily_graph = response['graph_dict'];

            var cal_data = [];
            var sug_data = [];
            var carbo_data = [];
            var pro_data = [];
            var fat_data = [];


            for(i=0; i<daily_graph.length; i++){
                cal_data[i] = {x: i, y: daily_graph[i]['calorie_value']};
                sug_data[i] = {x: i, y: daily_graph[i]['sugar_value']};
                carbo_data[i] = {x: i, y: daily_graph[i]['carbohydrate_value']};
                pro_data[i] = {x: i, y: daily_graph[i]['protein_value']};
                fat_data[i] = {x: i, y: daily_graph[i]['fat_value']};
            }

            var ctx = document.getElementById("daily-graph");
            var scatterChart = new Chart(ctx, {
                type: 'line',
                data: {
                    datasets: [
                        {
                            label: 'Calorie Value',
                            data: cal_data,
                            strokeColor: 'rgba(220,0,0,1)',
                            fill: false
                        },
                        {
                            label: 'Sugar Value',
                            data: sug_data,
                            fill: false
                        },
                        {
                            label: 'Carbohydrate Value',
                            data: carbo_data,
                            fill: false
                        },
                        {
                            label: 'Protein Value',
                            data: pro_data,
                            fill: false
                        },
                        {
                            label: 'Fat Value',
                            data: fat_data,
                            fill: false
                        }
                    ]
                },
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
            });

            var html_foods = "";
            var html_nutritions = "";
            for(i=0; i<foods.length; i++) {
                html_foods = html_foods + "<a href='food_page?food_id="+
                    foods[i]['food_id'] +"' class='list-group-item'><h4><p>"+
                    foods[i]['food_name'] +"</p></h4>" + "<p><strong>Date: </strong>"+
                    foods[i]['time_added'] +"</p>" + "<img src='"+
                    foods[i]['food_image'] +"' style='width: 100px; height: auto'></a>";
            }
            $("#consumption-panel").html(html_foods);

            html_nutritions = html_nutritions + "<p><strong>Carbohydrate: </strong>"+ nutritions['carbohydrate_value'] +" g</p>";
            html_nutritions = html_nutritions + "<p><strong>Sugar: </strong>"+ nutritions['sugar_value'] +" g</p>";
            html_nutritions = html_nutritions + "<p><strong>Fat: </strong>"+ nutritions['fat_value'] +" g</p>";
            html_nutritions = html_nutritions + "<p><strong>Protein: </strong>"+ nutritions['protein_value'] +" g</p>";
            html_nutritions = html_nutritions + "<p><strong>Calorie: </strong>"+ nutritions['calorie_value'] +" cal</p>";
            html_nutritions = html_nutritions + "<div hidden id='others'>"
            html_nutritions = html_nutritions + "<p><strong>Fiber: </strong>"+ nutritions['fiber_value'] +" g</p>";
            html_nutritions = html_nutritions + "<p><strong>Serving Weight: </strong>"+ nutritions['serving_weight_grams'] +" g</p>";
            html_nutritions = html_nutritions + "<h4><p><strong style='color: #5bc0de;'>Vitamins</strong></p></h4>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin A: </strong>"+ nutritions['vitamin_A'] +" IU</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin C: </strong>"+ nutritions['vitamin_C'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin D: </strong>"+ nutritions['vitamin_D'] +" IU</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin E: </strong>"+ nutritions['vitamin_E'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin K: </strong>"+ nutritions['vitamin_K'] +" mcg</p>";
            html_nutritions = html_nutritions + "<p><strong>Thiamin: </strong>"+ nutritions['thiamin'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Riboflavin: </strong>"+ nutritions['riboflavin'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Niacin: </strong>"+ nutritions['niacin'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin B6: </strong>"+ nutritions['vitamin_B6'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Vitamin B12: </strong>"+ nutritions['vitamin_B12'] +" mcg</p>";
            html_nutritions = html_nutritions + "<p><strong>Pantothenic acid: </strong>"+ nutritions['pantothenic_acid'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Choline: </strong>"+ nutritions['choline'] +" mg</p>";
            html_nutritions = html_nutritions + "<h4><p><strong style='color: #f0ad4e;'>Minerals</strong></p></h4>";
            html_nutritions = html_nutritions + "<p><strong>Calcium: </strong>"+ nutritions['calcium'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Copper: </strong>"+ nutritions['copper'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Flouride: </strong>"+ nutritions['flouride'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>IronFe: </strong>"+ nutritions['iron_Fe'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Magnesium: </strong>"+ nutritions['magnesium'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Manganese: </strong>"+ nutritions['manganese'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Sodium Na: </strong>"+ nutritions['sodium_Na'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Phosphorus: </strong>"+ nutritions['phosphorus'] +" mg</p>";
            html_nutritions = html_nutritions + "<p><strong>Selenium: </strong>"+ nutritions['selenium'] +" mcg</p>";
            html_nutritions = html_nutritions + "<p><strong>Zinc: </strong>"+ nutritions['zinc'] +" mg</p>";
            html_nutritions = html_nutritions + "</div>";
            html_nutritions = html_nutritions + "<a id='more-button'>More...</a>";


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
                    html_foods = html_foods + "<a href='food_page?food_id=" +
                        foods[i]['food_id'] +"' class='list-group-item'><h4><p>"+
                        foods[i]['food_name'] +"</p></h4>"+ "<p><strong>Date: </strong>"+
                        foods[i]['time_added'] +"</p>" + "<img src='"+
                        foods[i]['food_image'] +"' style='width: 100px; height: auto'></a>";
                }
                $("#consumption-panel").html(html_foods);
                html_nutritions = html_nutritions + "<p><strong>Carbohydrate: </strong>"+ nutritions['carbohydrate_value'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Sugar: </strong>"+ nutritions['sugar_value'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Fat: </strong>"+ nutritions['fat_value'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Protein: </strong>"+ nutritions['protein_value'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Calorie: </strong>"+ nutritions['calorie_value'] +"</p>";
                html_nutritions = html_nutritions + "<div hidden id='others'>"
                html_nutritions = html_nutritions + "<p><strong>Fiber: </strong>"+ nutritions['fiber_value'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Serving Weight: </strong>"+ nutritions['serving_weight_grams'] +"</p>";
                html_nutritions = html_nutritions + "<h4><p><strong style='color: #5bc0de;'>Vitamins</strong></p></h4>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin A: </strong>"+ nutritions['vitamin_A'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin C: </strong>"+ nutritions['vitamin_C'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin D: </strong>"+ nutritions['vitamin_D'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin E: </strong>"+ nutritions['vitamin_E'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin K: </strong>"+ nutritions['vitamin_K'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Thiamin: </strong>"+ nutritions['thiamin'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Riboflavin: </strong>"+ nutritions['riboflavin'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Niacin: </strong>"+ nutritions['niacin'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin B6: </strong>"+ nutritions['vitamin_B6'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Vitamin B12: </strong>"+ nutritions['vitamin_B12'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Pantothenic acid: </strong>"+ nutritions['pantothenic_acid'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Choline: </strong>"+ nutritions['choline'] +"</p>";
                html_nutritions = html_nutritions + "<h4><p><strong style='color: #f0ad4e;'>Minerals</strong></p></h4>";
                html_nutritions = html_nutritions + "<p><strong>Calcium: </strong>"+ nutritions['calcium'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Copper: </strong>"+ nutritions['copper'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Flouride: </strong>"+ nutritions['flouride'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>IronFe: </strong>"+ nutritions['iron_Fe'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Magnesium: </strong>"+ nutritions['magnesium'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Manganese: </strong>"+ nutritions['manganese'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Sodium Na: </strong>"+ nutritions['sodium_Na'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Phosphorus: </strong>"+ nutritions['phosphorus'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Selenium: </strong>"+ nutritions['selenium'] +"</p>";
                html_nutritions = html_nutritions + "<p><strong>Zinc: </strong>"+ nutritions['zinc'] +"</p>";
                html_nutritions = html_nutritions + "</div>";
                html_nutritions = html_nutritions + "<a id='more-button'>More...</a>";

                $("#nutrition-panel").html(html_nutritions);
            }
        });
    });
});

$(document).on("click", "#more-button", function () {
    var toggle = $(this).text();
    if(toggle == "More...") {
        $("#others").show();
        $(this).text("Less");
    } else {
        $("#others").hide();
        $(this).text("More...");
    }
});