/**
 * Created by alper on 23/11/2016.
 */
$(document).ready(function () {

    $('#star-rating').rating(function(vote, event){

        var food_id = $("#mark-as-eaten").data('foodid');
        $.ajax({
            url: "rate_food",
            method: 'post',
            data: {
                food_id: food_id,
                rate_value: vote
            },
            complete: function () {
                location.reload();
            }
        });
    });

    $("#search-tags").click(function () {
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

    $("#submit-tags").click(function () {

        var name = "";
        var id = "";
        var label = "";
        var description = "";
        var food_id = $(this).data('foodid');
        $(".sem-tag-selected").each(function () {
            name = $(this).data('name');
            id = $(this).attr('id');
            label = $(this).data('label');
            description = $(this).data('description');
        });
        $.ajax({
            url: 'tag_food',
            method: 'post',
            data: {
                tagged_food_id: food_id,
                tag_name: name,
                tag_id: id,
                tag_label: label,
                tag_description: description
            },
            complete: function () {
                location.reload();
            }
        });
    });

    $("#mark-as-eaten").click(function () {
        var food_id = $(this).data('foodid');
        var user_id = 1;
        $.ajax({
            url: 'mark_as_eaten',
            method: 'post',
            data: {
                user_id: user_id,
                food_id: food_id
            },
            success: function () {
                window.location = document.URL.substr(0,document.URL.lastIndexOf('/'));
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

    $("#add-comment").click(function () {
        var comment = $("#comment").val();
        var foodid = $("#mark-as-eaten").data('foodid');
        $.ajax({
            url: 'comment_food',
            method: 'post',
            data: {
                comment_text: comment,
                food_id: foodid
            },
            success: function (response) {
                location.reload();
            }
        });
    });
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