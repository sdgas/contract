var j = jQuery.noConflict();
j(document).ready(function () {

    /* 	1st example	*/

    /// wrap inner content of each anchor with first layer and append background layer
    j("#menu li a").wrapInner('<span class="out"></span>').append('<span class="bg"></span>');

    // loop each anchor and add copy of text content
    j("#menu li a").each(function () {
        j('<span class="over">' + j(this).text() + '</span>').appendTo(this);
    });

    j("#menu li a").hover(function () {
        // this function is fired when the mouse is moved over
        j(".out", this).stop().animate({'top': '45px'}, 250); // move down - hide
        j(".over", this).stop().animate({'top': '0px'}, 250); // move down - show
        j(".bg", this).stop().animate({'top': '0px'}, 120); // move down - show

    }, function () {
        // this function is fired when the mouse is moved off
        j(".out", this).stop().animate({'top': '0px'}, 250); // move up - show
        j(".over", this).stop().animate({'top': '-45px'}, 250); // move up - hide
        j(".bg", this).stop().animate({'top': '-45px'}, 120); // move up - hide
    });
});