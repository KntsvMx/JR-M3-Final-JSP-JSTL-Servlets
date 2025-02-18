// TODO: write requests for other buttons or actions like: restart, logout, register etc.

$(document).ready(function () {
    $('#startTestQuestButton').click(function () {
        $.ajax({
            url: '/start',
            type: 'GET',
            success: function (response) {
                window.location.href = response;
            },
            error: function (response) {
                if (response.status === 401) {
                    alert('Please login to start the test');
                    window.location.href = '/register.jsp';
                } else {
                    console.error('Error: ' + response.responseText);
                    alert('Failed to start the test');
                }
            }
        });
    });
    //
    // $('#logout').click(function () {
    //     logout();
    // });
    //
    // $('#restart').click(function () {
    //     restart();
    // });
    //
    // $('#register').click(function () {
    //     register();
    // });
});
