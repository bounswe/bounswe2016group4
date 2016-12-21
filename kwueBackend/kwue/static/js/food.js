/**
 * Created by alper on 09/11/2016.
 */
$(document).ready(function(){

    $("#calc-nutr").click(function () {

        var ings = [];
        $(".ing-group").each(function () {
            var fieldName = $(this).children("#ingredient-1").val();
            var fieldValue = $(this).children("#ingredient-1-val").val();
            var temp = {'ingredient': fieldName, 'value': fieldValue};
            ings.push(temp);
        });

        $.ajax({
            url: 'get_nutritional_values',
            method: 'post',
            data: {
                ingredients: JSON.stringify(ings)
            },
            success: function (nutritions) {
                var html_nutritions = "";
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

                $("#nutrition-values").html(html_nutritions);
            }
        })
    });

    $("#submit-button").click(function () {
        var data = $('#food-form').serializeArray();
        var ingredients = [];
        var tags = []
        var tobepushed = {};
        for(i=1; i<data.length; i++) {
            var fieldName = data[i]['name'];
            var fieldValue = data[i]['value'];
            tobepushed[fieldName] = fieldValue;

        }
        $(".ing-group").each(function () {
            var fieldName = $(this).children("#ingredient-1").val();
            var fieldValue = $(this).children("#ingredient-1-val").val();
            var temp = {'ingredient': fieldName, 'value': fieldValue};
            ingredients.push(temp);
        });

        $(".sem-tag-selected").each(function () {
            var name = $(this).data('name');
            var id = $(this).attr('id');
            var label = $(this).data('label');
            var description = $(this).data('description');
            var temp = {'tag_name': name, 'tag_id': id, 'tag_label': label, 'tag_description': description};
            tags.push(temp);
        });
        tobepushed['ingredients'] = JSON.stringify(ingredients);
        tobepushed['food_tags'] = JSON.stringify(tags);
        $.ajaxSetup({
            beforeSend: function(xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", data[0]['value']);
            }
        });
        $.ajax({
            url: "add_food",
            method: "post",
            data: tobepushed,
            success: function (e) {
                console.log(e);
            }
        });
    });

    $("#tag_button").click(function () {
        var tag_name = $("#sem-tags").val();
        $("#tag-result-panel").show();
        $.ajax({
            url: "search_semantic_tags",
            data: {
                tag_name: tag_name
            },
            success: function (result) {
                $("#semantic-tag-result").html(function () {
                    if(result.length==0) {
                        return("<p>no result</p>");
                    }
                    var res = "";
                    for(i=0; i<result.length; i++) {
                        res = res + "<h4><p><a id='"+
                            result[i]['tag_id'] +
                            "' class='sem-tag label label-default' data-label='"+
                            result[i]['tag_label'] +"' data-description='" +
                            result[i]['tag_description'] +"' data-name='"+ tag_name +"'>" +
                            result[i]['tag_label'] + ": " + result[i]['tag_description'] + "</a></p></h4>"
                    }
                    return res;
                });
            }
        });
    });

    $("#ingredient-add").click(function () {
        var html = "<div class='form-group ing-group'>" +
            "<label for='ingredient-1'>Ingredient</label>" +
            "<a class='btn btn-danger ingredient-delete' id='ingredient-delete'><span class='glyphicon glyphicon-minus-sign'></span></a>" +
            "<input type='text' class='form-control ing-gram' id='ingredient-1-val' value='' placeholder='Amount'>" +
            "<input type='text' class='form-control ing-name' id='ingredient-1' value='' placeholder='Ingredient name'>" +
            "</div>";
        $("#ingredient-add").before(html);
    })
});

$(document).on('click', '.ingredient-delete', function () {
    $(this).parent().remove();
});

$(document).on('click', '.sem-tag', function () {
    if( $(this).hasClass("label-default") ) {
        $(this).removeClass("label-default");
        $(this).addClass("label-info");
        var label_html = $(this).html();
        var id = $(this).attr('id');
        var label = $(this).data('label');
        var name = $(this).data('name');
        var description = $(this).data('description');
        $("#semantic-tag-selected").append(function () {
            var res = "<h4><p><a id='"+
                id +"' class='sem-tag-selected label label-success' data-label='"+
                label +"' data-name='"+
                name +"' data-description='"+
                description +"'>" + label_html +"</a></p></h4>"
            return res;
        });

    } else {
        $(this).removeClass("label-info");
        $(this).addClass("label-default");
        var label_id = $(this).attr('id');
        $("#semantic-tag-selected").find("#" + label_id).remove();
    }
});

$(document).on('click', ".sem-tag-selected", function () {
    var label_id = $(this).attr('id');
    $("#semantic-tag-result").find("#" + label_id).removeClass("label-info");
    $("#semantic-tag-result").find("#" + label_id).addClass("label-default");
    $(this).remove();
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