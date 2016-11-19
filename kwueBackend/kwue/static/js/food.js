/**
 * Created by alper on 09/11/2016.
 */
$(document).ready(function(){
    $("#555").click(function(){
        $("#555").hide();
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
            "<input type='text' class='form-control' id='ingredient-1' value=''>" +
            "<input type='number' class='form-control' id='ingredient-1-val' value=''>" +
            "</div>"
        $("#ingredient-add").before(html);
    })
});

$(document).on('click', '.ingredient-delete', function () {
    $(this).parent().remove();
})