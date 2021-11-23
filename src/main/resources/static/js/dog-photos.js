$(document).ready(function () {
    $('.carousel-inner').children().eq(1).addClass('active');

    $('#dog-photo-trigger-delete').click(function () {
        $('.dog-photo-delete-form').each(function (index, form) {
            if (form.parentElement.classList.contains('active')) {
                $('#confirm-dog-photo-delete').click(function() {
                    form.submit();
                });
            }
        });
    });
});