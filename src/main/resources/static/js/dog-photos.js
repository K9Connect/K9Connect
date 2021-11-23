$(document).ready(function () {
    $('.carousel-item:first-child').addClass('active');

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