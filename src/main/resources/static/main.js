"use strict";
(function() {
    if (document.querySelector('#delete-post-btn')) {
        document.querySelector('#delete-post-btn').addEventListener('click', e => {
            e.preventDefault();
            const willDelete = confirm('Are you sure you want to delete this post?');
            if (willDelete) {
                e.currentTarget.parentNode.submit();
            }
        });
    }
})();
document.querySelectorAll('.datepicker').forEach(function(field) {
    var picker = new Pikaday({
        field: field
    });
});
