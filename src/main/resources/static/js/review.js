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
            bones[i].css('color', '#e3e3e3');
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

    const buriedBone = $('#buried-bone');

    const bones = [
        null,
        $('#user-review-bone-1'),
        $('#user-review-bone-2'),
        $('#user-review-bone-3'),
        $('#user-review-bone-4'),
        $('#user-review-bone-5')
    ];

    setHoverListeners();

    for (let i = 1; i < bones.length; i++) {
        bones[i].click(setBones);
    }
});