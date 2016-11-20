/**
 * Created by alper on 09/11/2016.
 */
$(document).ready(function(){
    $("#submit-button").click(function () {
        var data = $('#food-form').serializeArray();
        var ingredients = [];
        $(".ing-group").each(function () {
            var name = $(this).children("#ingredient-1").val();
            var value = $(this).children("#ingredient-1-val").val();
            var temp = {'ingredient': name, 'value': value};
        });
        data.push({name: 'ingredients', value: ingredients});
        $.post("add_food", data);
    });

    $("#tag_button").click(function () {
        var tag_name = $("#sem-tags").val();
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
                        res = res + "<p>" + result[i]['itemLabel'] + ": " + result[i]['itemDescription'] + "</p>"
                    }
                    return res;
                });
            }
        })
    });

    $("#ingredient-add").click(function () {
        var html = "<div class='form-group ing-group'>" +
            "<label for='ingredient-1'>Ingredient 1</label>" +
            "<a href='#' class='btn btn-danger ingredient-delete' id='ingredient-delete'><span class='glyphicon glyphicon-minus-sign'></span></a>" +
            "<input type='text' class='form-control ing-name' id='ingredient-1' value='' placeholder='Ingredient name'>" +
            "<input type='number' class='form-control ing-gram' id='ingredient-1-val' value='' placeholder='Amount in grams' min='1'>" +
            "</div>";
        $("#ingredient-add").before(html);
    })
});

$(document).on('click', '.ingredient-delete', function () {
    $(this).parent().remove();
});