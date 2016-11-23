/**
 * Created by alper on 23/11/2016.
 */
$(document).ready(function () {
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
        var user_id = $(this).data('userid');
        $(".sem-tag-selected").each(function () {
            name = $(this).data('name');
            id = $(this).attr('id');
            label = $(this).data('label');
            description = $(this).data('description');
        });
        $.ajax({
            url: 'tag_user',
            method: 'post',
            data: {
                tagged_user_id: user_id,
                tag_name: name,
                tag_id: id,
                tag_label: label,
                tag_description: description
            },
            success: function () {
                $("#submit-tags").removeClass("btn-warning");
                $("#submit-tags").addClass("btn-success");
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
})