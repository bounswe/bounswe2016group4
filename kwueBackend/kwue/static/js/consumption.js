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


            for(i=0; i<7; i++){
                cal_data[i] = {x: i+1, y: daily_graph[i+23]['calorie_value']};
                sug_data[i] = {x: i+1, y: daily_graph[i+23]['sugar_value']};
                carbo_data[i] = {x: i+1, y: daily_graph[i+23]['carbohydrate_value']};
                pro_data[i] = {x: i+1, y: daily_graph[i+23]['protein_value']};
                fat_data[i] = {x: i+1, y: daily_graph[i+23]['fat_value']};
            }

            var ctx = document.getElementById("daily-graph");
            var scatterChart = new Chart(ctx, {
                type: 'line',
                data: {
                    datasets: [
                        // {
                        //     label: 'Calorie',
                        //     data: cal_data,
                        //     borderColor: "rgba(118, 28, 25, 1)",
                        //     backgroundColor: "rgba(118, 28, 25, 0.2)",
                        //     fill: false
                        // },
                        {
                            label: 'Sugar',
                            borderColor: "rgba(140, 140, 140, 1)",
                            backgroundColor: "rgba(140, 140, 140, 0.2)",
                            data: sug_data,
                            fill: false
                        },
                        {
                            label: 'Carbohydrate',
                            borderColor: "rgba(62, 143, 62, 1)",
                            backgroundColor: "rgba(62, 143, 62, 0.2)",
                            data: carbo_data,
                            fill: false
                        },
                        {
                            label: 'Protein',
                            borderColor: "rgba(217, 83, 79, 1)",
                            backgroundColor: "rgba(217, 83, 79, 0.2)",
                            data: pro_data,
                            fill: false
                        },
                        {
                            label: 'Fat',
                            borderColor: "rgba(152, 95, 13, 1)",
                            backgroundColor: "rgba(152, 95, 13, 0.2)",
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

            var contx = document.getElementById("calorie-graph");
            var scatterChart2 = new Chart(contx, {
                type: 'line',
                data: {
                    datasets: [
                        {
                            label: 'Calorie',
                            data: cal_data,
                            borderColor: "rgba(118, 28, 25, 1)",
                            backgroundColor: "rgba(118, 28, 25, 0.2)",
                            fill: false
                        },
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
            html_nutritions = html_nutritions + "<p><strong>Calorie: </strong>"+ nutritions['calorie_value'] +" kcal</p>";
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
        var k = 0;

        if(setting == "daily" || setting == "weekly") {
            k = 23;
        } else {
            k = 0;
        }



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
                var daily_graph = response['graph_dict'];
                var html_foods = "";
                var html_nutritions = "";

                var cal_data = [];
                var sug_data = [];
                var carbo_data = [];
                var pro_data = [];
                var fat_data = [];

                for(i=0; i<30-k; i++){
                    cal_data[i] = {x: i+1, y: daily_graph[i+k]['calorie_value']};
                    sug_data[i] = {x: i+1, y: daily_graph[i+k]['sugar_value']};
                    carbo_data[i] = {x: i+1, y: daily_graph[i+k]['carbohydrate_value']};
                    pro_data[i] = {x: i+1, y: daily_graph[i+k]['protein_value']};
                    fat_data[i] = {x: i+1, y: daily_graph[i+k]['fat_value']};
                }

                var ctx = document.getElementById("daily-graph");
                var scatterChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        datasets: [
                            {
                                label: 'Sugar',
                                borderColor: "rgba(140, 140, 140, 1)",
                                backgroundColor: "rgba(140, 140, 140, 0.2)",
                                data: sug_data,
                                fill: false
                            },
                            {
                                label: 'Carbohydrate',
                                borderColor: "rgba(62, 143, 62, 1)",
                                backgroundColor: "rgba(62, 143, 62, 0.2)",
                                data: carbo_data,
                                fill: false
                            },
                            {
                                label: 'Protein',
                                borderColor: "rgba(217, 83, 79, 1)",
                                backgroundColor: "rgba(217, 83, 79, 0.2)",
                                data: pro_data,
                                fill: false
                            },
                            {
                                label: 'Fat',
                                borderColor: "rgba(152, 95, 13, 1)",
                                backgroundColor: "rgba(152, 95, 13, 0.2)",
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

                var contx = document.getElementById("calorie-graph");
                var scatterChart2 = new Chart(contx, {
                    type: 'line',
                    data: {
                        datasets: [
                            {
                                label: 'Calorie',
                                data: cal_data,
                                borderColor: "rgba(118, 28, 25, 1)",
                                backgroundColor: "rgba(118, 28, 25, 0.2)",
                                fill: false
                            },
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





                for(i=0; i<foods.length; i++) {
                    html_foods = html_foods + "<a href='food_page?food_id=" +
                        foods[i]['food_id'] +"' class='list-group-item'><h4><p>"+
                        foods[i]['food_name'] +"</p></h4>"+ "<p><strong>Date: </strong>"+
                        foods[i]['time_added'] +"</p>" + "<img src='"+
                        foods[i]['food_image'] +"' style='width: 100px; height: auto'></a>";
                }
                $("#consumption-panel").html(html_foods);
                html_nutritions = html_nutritions + "<p><strong>Carbohydrate: </strong>"+ nutritions['carbohydrate_value'] +" g</p>";
                html_nutritions = html_nutritions + "<p><strong>Sugar: </strong>"+ nutritions['sugar_value'] +" g</p>";
                html_nutritions = html_nutritions + "<p><strong>Fat: </strong>"+ nutritions['fat_value'] + " g</p>";
                html_nutritions = html_nutritions + "<p><strong>Protein: </strong>"+ nutritions['protein_value'] +" g</p>";
                html_nutritions = html_nutritions + "<p><strong>Calorie: </strong>"+ nutritions['calorie_value'] +" kcal</p>";
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
                html_nutritions = html_nutritions + "<p><strong>Vitamin B12: </strong>"+ nutritions['vitamin_B12'] +" mg</p>";
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