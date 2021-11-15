$(document).ready(function() {
    function setBones() {
        resetColor();
        let theBones = $(this).data('bones');
        goGold(theBones);
        removeHoverListeners();
        buriedBone.val(theBones);

        // console.log(`You rated this user ${buriedBone.val()} bones.`);
    }

    function goGold(boneCount) {
        for (let i = 1; i <= boneCount; i++) {
            bones[i].css('color', 'gold');
        }
    }

    function resetColor() {
        for (let i = 1; i < bones.length; i++) {
            bones[i].css('color', '#808088');
        }
    }

    function setHoverListeners() {
        for (let i = 1; i < bones.length; i++) {
            bones[i].on('mouseenter', function() {
                goGold($(this).data('bones'));
            });
            bones[i].on('mouseout', resetColor);
        }
    }

    function removeHoverListeners() {
        for (let i = 1; i < bones.length; i++) {
            bones[i].off('mouseenter');
            bones[i].off('mouseout');
        }
    }

    const buriedBone = $('#buried-dog-bone');

    const bones = [
        null,
        $('#dog-review-bone-1'),
        $('#dog-review-bone-2'),
        $('#dog-review-bone-3'),
        $('#dog-review-bone-4'),
        $('#dog-review-bone-5')
    ];

    setHoverListeners();

    for (let i = 1; i < bones.length; i++) {
        bones[i].click(setBones);
    }
});