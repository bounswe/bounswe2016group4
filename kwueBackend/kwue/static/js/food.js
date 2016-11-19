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

    })
    
});